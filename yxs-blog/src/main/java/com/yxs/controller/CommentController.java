package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: CommentController
 * @Desription:
 * @date 2023/3/20 14:59
 */
@RestController("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {

        return commentService.commentList(articleId, pageNum, pageSize);

    }

}
