package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;

public interface BlogLoginService extends IService<User> {

    ResponseResult login(User user);

    ResponseResult logout();

}
