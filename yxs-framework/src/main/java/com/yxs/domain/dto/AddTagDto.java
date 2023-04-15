package com.yxs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.dto
 * @ClassName: AddListDto
 * @Desription:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTagDto {

    private String name;
    private String remark;

}
