package com.yxs.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.vo
 * @ClassName: ExcelCategoryVo
 * @Desription:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCategoryVo {

    @ExcelProperty("分类名")
    private String name;

    @ExcelProperty("描述") // 描述
    private String description;

    @ExcelProperty("状态0: 正常 1禁用") // 状态0: 正常 1禁用
    private String status;

}
