package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Menu;
import com.yxs.domain.entity.User;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.handler.exception.SystemException;
import com.yxs.service.AdminService;
import com.yxs.vo.AdminUserInfoVo;
import com.yxs.vo.RoutersVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: LoginController
 * @Desription:
 * @date 2023/3/22 19:31
 */
@RestController
public class AdminLoginController {

    @Resource
    private AdminService adminService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {

        if (!StringUtils.hasText(user.getUserName())) throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        return adminService.login(user);

    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {

        return adminService.getInfo();

    }
    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters() {

        return adminService.getRouters();

    }

}
