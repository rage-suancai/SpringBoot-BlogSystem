package com.yxs.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sg_comment")
public class Comment {

    @TableId
    private Long id;

    private String type; // 评论类型(0代表文章评论 1代表友链评论)

    private Long articleId; // 文章id

    private Long rootId; // 根评论id

    private String content; // 评论内容

    private Long toCommentUserId; // 所回复的目标评论的userid

    private Long toCommentId; // 回复目标评论id

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer delFlag; // 删除标志(0代表未删除 1代表已删除)

}

