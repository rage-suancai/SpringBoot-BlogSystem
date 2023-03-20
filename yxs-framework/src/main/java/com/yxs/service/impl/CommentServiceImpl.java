package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Comment;
import com.yxs.mapper.CommentMapper;
import com.yxs.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: CommentServiceImpl
 * @Desription:
 * @date 2023/3/20 15:06
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    // private

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {


        return null;


    }

}
