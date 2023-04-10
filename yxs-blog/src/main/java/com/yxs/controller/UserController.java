package com.yxs.controller;

import com.yxs.annotation.SystemLog;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;
import com.yxs.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: UserController
 * @Desription:
 * @date 2023/3/21 10:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/userInfo")
    public final ResponseResult userInfo() {
        return userService.userInfo();
    }
    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public final ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PutMapping
    public final ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }

}
