package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: CommentVo
 * @Desription:
 * @date 2023/3/20 15:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    private Long id;

    private Long articleId; // 文章id

    private Long rootId; // 根评论id

    private String content; // 评论内容

    private Long toCommentUserId; // 所回复的目标评论的userid

    private String setToCommentUserName;

    private Long toCommentId; // 回复目标评论id

    private Long createBy;

    private Date createTime;

    private String username;

    private List<CommentVo> children;

}
