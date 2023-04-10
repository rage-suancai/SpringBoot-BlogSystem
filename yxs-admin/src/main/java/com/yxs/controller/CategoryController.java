package com.yxs.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Category;
import com.yxs.domain.vo.CategoryVo;
import com.yxs.domain.vo.ExcelCategoryVo;
import com.yxs.domain.vo.PageVo;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.service.CategoryService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.WebUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: CategoryController
 * @Desription:
 * @date 2023/4/7 16:43
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        return ResponseResult.okResult(categoryService.listAllCategory());
    }

    @PutMapping
    public ResponseResult editCategory(@RequestBody Category category) {

        categoryService.updateById(category);
        return ResponseResult.okResult();

    }

    @GetMapping(value = "/{id}")
    public ResponseResult getCategoryInfo(@PathVariable(value = "id") Long id) {
        return ResponseResult.okResult(categoryService.getById(id));
    }

    @PostMapping
    public ResponseResult addCategory(@RequestBody Category category) {

        categoryService.save(category);
        return ResponseResult.okResult();

    }

    @GetMapping("/list")
    public ResponseResult listCategory(Category category, Integer pageNum, Integer pageSize) {
        return ResponseResult.okResult(categoryService.selectCategoryPage(category, pageNum, pageSize));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult removeCategory(@PathVariable(value = "id") Long id) {

        categoryService.removeById(id);
        return ResponseResult.okResult();

    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {

        try {
            WebUtils.setDownLoadHeader("分类.xlsx", response); // 设置下载文件的请求头
            List<Category> categories = categoryService.list(); // 获取需要导入的数据

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categories, ExcelCategoryVo.class);
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class) // 把数据写入excel
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR); // 出现异常也要响应json
            WebUtils.renderString(response, JSON.toJSONString(result));
        }

    }

}
