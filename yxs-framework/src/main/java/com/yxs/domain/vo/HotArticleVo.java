package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.entity
 * @ClassName: HotArticleVo
 * @Desription:
 * @date 2023/3/18 2:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    private Long id;
    private String title; // 标题
    private Long viewCount; // 访问量

}
