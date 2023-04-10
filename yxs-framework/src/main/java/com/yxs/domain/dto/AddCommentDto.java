package com.yxs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDto {

    private Long id;

    private String type; // 评论类型(0代表文章评论 1代表友链评论)

    private Long articleId; // 文章id

    private Long rootId; // 根评论id

    private String content; // 评论内容

    private Long toCommentUserId; // 所回复的目标评论的userid

    private Long toCommentId; // 回复目标评论id

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag; // 删除标志(0代表未删除 1代表已删除)

}
