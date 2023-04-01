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
 * @date 2023/4/1 11:03
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

        PageVo pageVo = articleService.selectArticlePage(article, pageNum, pageSize);
        return ResponseResult.okResult(pageVo);

    }

    @GetMapping(value = "/{id}")
    public ResponseResult getArticleInfo(@PathVariable(value = "id") Long id) {

        ArticleVo articleVo = articleService.getArticleInfo(id);
        return ResponseResult.okResult(articleVo);

    }

    @PutMapping
    public ResponseResult editArticle(@RequestBody ArticleDto articleDto) {

        articleService.editArticle(articleDto);
        return ResponseResult.okResult();

    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable Long id) {

        articleService.removeById(id);
        return ResponseResult.okResult();

    }

}
