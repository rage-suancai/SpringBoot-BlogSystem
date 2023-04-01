package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: PageVo
 * @Desription:
 * @date 2023/3/18 21:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    private List rows;
    private Long total;

}
