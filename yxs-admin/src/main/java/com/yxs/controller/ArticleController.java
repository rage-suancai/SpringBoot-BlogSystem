package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.AddArticleDto;
import com.yxs.domain.dto.ArticleDto;
import com.yxs.domain.entity.Article;
import com.yxs.domain.vo.ArticleVo;
import com.yxs.domain.vo.PageVo;
import com.yxs.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: ArticleController
 * @Desription:
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto articleDto) {
        return articleService.addArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult listArticle(Article article, Integer pageNum, Integer pageSize) {
        return ResponseResult.okResult(articleService.selectArticlePage(article, pageNum, pageSize));
    }

    @GetMapping(value = "/{id}")
    public ResponseResult getArticleInfo(@PathVariable(value = "id") Long id) {
        return ResponseResult.okResult(articleService.getArticleInfo(id));
    }

    @PutMapping
    public ResponseResult editArticle(@RequestBody ArticleDto articleDto) {

        articleService.editArticle(articleDto);
        return ResponseResult.okResult();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult deleteArticle(@PathVariable Long id) {

        articleService.removeById(id);
        return ResponseResult.okResult();

    }

}
