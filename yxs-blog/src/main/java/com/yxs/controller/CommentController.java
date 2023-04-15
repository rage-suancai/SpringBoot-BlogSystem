package com.yxs.controller;

import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Comment;
import com.yxs.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: CommentController
 * @Desription:
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult comment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

}
