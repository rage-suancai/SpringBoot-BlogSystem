package com.yxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: ArticleListVo
 * @Desription:
 * @date 2023/3/18 21:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {

    private Long id;

    private String title; // 标题

    private String summary; // 文章摘要

    private String categoryName; // 所属分类名

    private String thumbnail; // 缩略图

    private Long viewCount; // 访问量

    private Date createTime;

}
