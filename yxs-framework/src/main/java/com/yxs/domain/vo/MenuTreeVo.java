package com.yxs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MenuTreeVo {

    private static final long serialVersionUID = 1L;

    private Long id; // 节点id

    private String label; // 节点名称

    private Long parentId;

    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeVo> children; // 子节点

}
