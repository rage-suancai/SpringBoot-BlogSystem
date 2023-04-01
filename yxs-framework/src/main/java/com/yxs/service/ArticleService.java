package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.AddArticleDto;
import com.yxs.domain.dto.ArticleDto;
import com.yxs.domain.entity.Article;
import com.yxs.domain.vo.ArticleVo;
import com.yxs.domain.vo.PageVo;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.mapper
 * @ClassName: ArticleService
 * @Desription:
 * @date 2023/3/18 0:16
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto articleDto);

    PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize);

    ArticleVo getArticleInfo(Long id);

    void editArticle(ArticleDto articleDto);

}
