package com.yxs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.dto
 * @ClassName: EditTagDto
 * @Desription:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTagDto {

    private Long id;
    private String name;
    private String remark;

}
