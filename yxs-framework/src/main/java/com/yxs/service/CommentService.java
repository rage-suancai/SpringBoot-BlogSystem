package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Comment;

public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

}
