package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: ArticleController
 * @Desription:
 * @date 2023/3/18 0:24
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {

        ResponseResult result = articleService.hotArticleList();
        return result;

    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        return articleService.articleList(pageNum, pageSize, categoryId);

    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {

        return articleService.getArticleDetail(id);

    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {

        return articleService.updateViewCount(id);

    }

}
