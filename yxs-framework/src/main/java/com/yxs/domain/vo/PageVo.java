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
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    private List rows;
    private Long total;

}
