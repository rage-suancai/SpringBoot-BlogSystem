package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: LinkVo
 * @Desription:
 * @date 2023/3/19 0:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {

    private String address; // 网站地址

    private String description;

    private Long id;

    private String logo;

    private String name;

}
