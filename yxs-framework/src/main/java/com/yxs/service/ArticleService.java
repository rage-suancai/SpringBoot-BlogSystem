package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Article;

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

}
