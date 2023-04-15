package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: CategoryVO
 * @Desription:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    private Long id; // 主键
    private String name; // 分类名
    private String description; // 描述

}
