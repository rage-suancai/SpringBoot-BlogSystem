package com.yxs.service.impl;

import com.yxs.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: PermissionService
 * @Desription:
 */
@Service("ps")
public class PermissionService {

    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission) {

        if (SecurityUtils.isAdmin()) return true; // 是超级管理员 直接过
        // 否则 获取当前登录用户所具有的权限列表判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);

    }

}
