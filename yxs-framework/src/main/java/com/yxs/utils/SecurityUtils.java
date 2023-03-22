package com.yxs.utils;

import com.yxs.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author YXS
 * @PackageName: com.yxs.utils
 * @ClassName: SecurityUtils
 * @Desription:
 * @date 2023/3/20 22:38
 */
public class SecurityUtils {


    // 获取用户
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }


    // 获取Authentication
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {

        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;

    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }

}
