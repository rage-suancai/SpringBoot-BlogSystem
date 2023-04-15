package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Comment;
import com.yxs.domain.vo.CommentVo;
import com.yxs.domain.vo.PageVo;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.handler.exception.SystemException;
import com.yxs.mapper.CommentMapper;
import com.yxs.service.CommentService;
import com.yxs.service.UserService;
import com.yxs.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: CommentServiceImpl
 * @Desription:
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId); // 对articleId进行判断
        queryWrapper.eq(Comment::getRootId, -1); // 根评论 rootId为-1
        queryWrapper.eq(Comment::getType, commentType); // 评论类型
        Page<Comment> page = new Page<>(pageNum, pageSize); // 分页
        page(page, queryWrapper);

        /*List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        for (CommentVo commentVo : commentVoList) { // 查询所有根评论对应的子评论集合 并赋值给对应属性

            List<CommentVo> children = getChildren(commentVo.getId()); // 查询对子评论
            commentVo.setChildren(children); // 赋值

        }*/

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        commentVoList.forEach(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())));
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));

    }

    @Override
    public ResponseResult addComment(Comment comment) {

        // 评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())) throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        save(comment);
        return ResponseResult.okResult();

    }

    private List<CommentVo> getChildren(Long id) { // 根据评论id查询对应子评论集合

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;

    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {

        /*List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        for (CommentVo commentVo : commentVos) { // 遍历vo集合

            String nickName = userService.getById(commentVo.getCreateBy()).getNickName(); // 通过toCommentUserId查询用户昵称并赋值
            commentVo.setUsername(nickName);
            if (commentVo.getToCommentId() != -1) { // 如果toCommentUserId不为-1才查询

                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setSetToCommentUserName(toCommentUserName);

            }

        }
        return commentVos;*/

        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class).stream()
                .peek(commentVo -> commentVo.setUsername(userService.getById(commentVo.getCreateBy()).getNickName()))
                .peek(commentVo -> commentVo.setSetToCommentUserName(commentVo.getToCommentUserId() != -1 ?
                        userService.getById(commentVo.getToCommentUserId()).getNickName() : ""))
                .collect(Collectors.toList());
        return commentVos;

    }

}
