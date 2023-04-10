package com.yxs.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.entity
 * @ClassName: RoleMenu
 * @Desription:
 * @date 2023/4/10 9:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_menu")
public class RoleMenu {

    private Long roleId; // 角色ID

    private Long menuId; // 菜单ID

}
