package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Article;
import com.yxs.domain.entity.Category;
import com.yxs.mapper.ArticleMapper;
import com.yxs.service.ArticleService;
import com.yxs.service.CategoryService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.vo.ArticleDetailVo;
import com.yxs.vo.ArticleListVo;
import com.yxs.vo.HotArticleVo;
import com.yxs.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.service.impl
 * @ClassName: ArticleServiceImpl
 * @Desription:
 * @date 2023/3/18 0:18
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>(); // 封装成ResponseResult返回

        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL); // 不能是草稿
        queryWrapper.orderByDesc(Article::getViewCount); // 排序
        Page<Article> page = new Page(1, 10); // 最多10条
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();

        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class); // bean拷贝
        return ResponseResult.okResult(vs);

    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        // 查询条件 如果有categoryId就要查询时要和传入的相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL); // 是正式发布的文章
        queryWrapper.orderByDesc(Article::getIsTop); // 对isTop降序

        Page<Article> page = new Page<>(pageNum, pageSize); // 分页查询
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();

        articles.stream() // 查询categoryName
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class); // 封装查询结果
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult getArticleDetail(Long id) {

        Article article = getById(id); // id查询文章
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class); // 转换vo
        Long categoryId = articleDetailVo.getCategoryId(); // 根据分类id查询分类名
        Category category = categoryService.getById(categoryId);

        if (category != null) articleDetailVo.setCategoryName(category.getName());
        return ResponseResult.okResult(articleDetailVo); // 封装响应返回

    }

}
