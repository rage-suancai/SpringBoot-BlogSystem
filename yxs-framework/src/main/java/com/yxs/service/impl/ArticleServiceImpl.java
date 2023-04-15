package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.AddArticleDto;
import com.yxs.domain.dto.ArticleDto;
import com.yxs.domain.entity.Article;
import com.yxs.domain.entity.ArticleTag;
import com.yxs.domain.entity.Category;
import com.yxs.domain.vo.*;
import com.yxs.mapper.ArticleMapper;
import com.yxs.service.ArticleService;
import com.yxs.service.ArticleTagService;
import com.yxs.service.CategoryService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.RedisCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.service.impl
 * @ClassName: ArticleServiceImpl
 * @Desription:
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private CategoryService categoryService;
    @Resource
    private ArticleTagService articleTagService;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>(); // 封装成ResponseResult返回

        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL); // 不能是草稿
        queryWrapper.orderByDesc(Article::getViewCount); // 排序
        Page<Article> page = new Page<>(1, 10); // 最多10条
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

    @Override
    public ResponseResult updateViewCount(Long id) {

        redisCache.incrementCacheMapValue(SystemConstants.VIEW_COUNT, id.toString(), 1);
        return ResponseResult.okResult();

    }

    @Override
    @Transactional
    public ResponseResult addArticle(AddArticleDto articleDto) {

        Article article = BeanCopyUtils.copyBean(articleDto, Article.class); // 添加博客
        save(article);

        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        articleTagService.saveBatch(articleTags); // 添加博客和标签的关联
        return ResponseResult.okResult();

    }

    @Override
    public PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(article.getTitle()), Article::getTitle, article.getTitle());
        queryWrapper.like(StringUtils.hasText(article.getSummary()), Article::getSummary, article.getSummary());
        Page<Article> page = new Page<>();
        page.setCurrent(pageNum); page.setSize(pageSize);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords(); // 转换成VO
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, ArticleVo.class);

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal()); pageVo.setRows(articleVos);
        return pageVo;

    }

    @Override
    public ArticleVo getArticleInfo(Long id) {

        Article article = getById(id);
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(ArticleTag::getArticleId, article.getId()); // 获取关联标签
        List<ArticleTag> articleTags = articleTagService.list(queryWrapper);
        List<Long> tags = articleTags.stream()
                .map(articleTag -> articleTag.getTagId())
                .collect(Collectors.toList());

        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        articleVo.setTags(tags);
        return articleVo;

    }

    @Override
    public void editArticle(ArticleDto articleDto) {

        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        updateById(article); // 更新博客信息

        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, article.getId()); // 删除原有的标签和博客的关联
        articleTagService.remove(queryWrapper);

        List<ArticleTag> articleTags = articleDto.getTags().stream() // 添加新的博客和标签的关联信息
                .map(tagId -> new ArticleTag(articleDto.getId(), tagId))
                .collect(Collectors.toList());

        articleTagService.saveBatch(articleTags);

    }

}
