package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;
import com.yxs.vo.AdminUserInfoVo;
import com.yxs.vo.RoutersVo;

public interface AdminService extends IService<User> {

    ResponseResult login(User user);

    ResponseResult<AdminUserInfoVo> getInfo();

    ResponseResult<RoutersVo> getRouters();

}
