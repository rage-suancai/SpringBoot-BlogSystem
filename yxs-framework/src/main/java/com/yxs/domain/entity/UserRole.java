package com.yxs.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YXS
 * @PackageName: com.yxs.domain.entity
 * @ClassName: UserRole
 * @Desription:
 * @date 2023/4/10 9:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
public class UserRole {

    private Long userId; // 用户ID

    private Long roleId; // 角色ID

}
