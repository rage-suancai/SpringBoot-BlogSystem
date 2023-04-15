package com.yxs.domain.vo;

import com.yxs.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.vo
 * @ClassName: RoutersVo
 * @Desription:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutersVo {

    private List<Menu> menus;

}
