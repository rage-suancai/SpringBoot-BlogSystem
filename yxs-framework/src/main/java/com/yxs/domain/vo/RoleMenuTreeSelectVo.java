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
public class RoleMenuTreeSelectVo {

    private List<Long> checkedKeys;

    private List<MenuTreeVo> menus;

}
