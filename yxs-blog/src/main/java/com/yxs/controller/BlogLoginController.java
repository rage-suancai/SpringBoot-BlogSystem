package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;
import com.yxs.service.BlogLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: BlogLoginController
 * @Desription:
 * @date 2023/3/19 2:45
 */
@RestController
public class BlogLoginController {

    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping
    public ResponseResult login(@RequestBody User user) {

        return blogLoginService.login(user);

    }

}
