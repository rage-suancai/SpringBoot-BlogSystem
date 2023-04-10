package com.yxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize);

    List<Role> selectRoleAll();

    void insertRole(Role role);

    void updateRole(Role role);

    List<Long> selectRoleIdByUserId(Long userId);


}

