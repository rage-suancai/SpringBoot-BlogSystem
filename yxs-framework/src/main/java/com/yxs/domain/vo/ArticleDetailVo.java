package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: ArticleDetailVo
 * @Desription:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {

    private Long categoryId; // 所属分类id

    private String categoryName; // 所属分类名

    private String content; // 文章内容

    private Date createTime;

    private String summary; // 文章摘要

    private String thumbnail; // 缩略图

    private Long id;

    private String isComment;

    private String title; // 标题

    private Long viewCount; // 访问量

}
