package com.yxs.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sg_article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 625337492348897098L;

    private Long articleId; // 文章id

    private Long tagId; // 标签id

}

