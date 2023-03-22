package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;

public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

}
