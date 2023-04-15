package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Article;
import com.yxs.domain.entity.Category;
import com.yxs.domain.vo.CategoryVo;
import com.yxs.domain.vo.PageVo;
import com.yxs.mapper.CategoryMapper;
import com.yxs.service.ArticleService;
import com.yxs.service.CategoryService;
import com.yxs.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: CategoryServiceImpl
 * @Desription:
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();

        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL); // 查询已发布文章
        List<Article> articleList = articleService.list(articleWrapper);

        // 获取文章分类id 去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 查分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class); // 封装vo
        return ResponseResult.okResult(categoryVos);

    }

    @Override
    public List<CategoryVo> listAllCategory() {

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Category::getStatus, SystemConstants.STATUS_NORMAL);
        List<Category> list = list(queryWrapper);

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;

    }

    @Override
    public PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(category.getName()), Category::getName, category.getName());
        queryWrapper.eq(Objects.nonNull(category.getStatus()), Category::getStatus, category.getStatus());
        Page<Category> page = new Page<>();
        page.setCurrent(pageNum); page.setSize(pageSize);
        page(page, queryWrapper);

        List<Category> categories = page.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal()); pageVo.setRows(categories);
        return pageVo;

    }

}
