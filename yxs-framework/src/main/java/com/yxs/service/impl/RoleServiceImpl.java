package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.entity.Role;
import com.yxs.mapper.RoleMapper;
import com.yxs.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {

        if (id == 1L) {
            List<String> roleKeys = new ArrayList<>(); // 判断是否是管理员 如果是返回集合中只需要有admin
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleKeyByUserId(id); // 否则查询用户具有的角色信息

    }

}

