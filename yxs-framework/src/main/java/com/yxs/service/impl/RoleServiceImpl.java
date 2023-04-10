package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Role;
import com.yxs.domain.entity.RoleMenu;
import com.yxs.domain.vo.PageVo;
import com.yxs.mapper.RoleMapper;
import com.yxs.service.RoleMenuService;
import com.yxs.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {

        if (id == 1L) {
            List<String> roleKeys = new ArrayList<>(); // 判断是否是管理员 如果是返回集合中只需要有admin
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleKeyByUserId(id); // 否则查询用户具有的角色信息

    }

    @Override
    public ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();

        // 目前没有根据id查询
        queryWrapper.like(StringUtils.hasText(role.getRoleName()), Role::getRoleName, role.getRoleName());
        queryWrapper.eq(StringUtils.hasText(role.getStatus()), Role::getStatus, role.getStatus());
        queryWrapper.orderByAsc(Role::getRoleSort);
        Page<Role> page = new Page<>();
        page.setCurrent(pageNum); page.setSize(pageSize);
        page(page, queryWrapper);

        List<Role> roles = page.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal()); pageVo.setRows(roles);
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public List<Role> selectRoleAll() {
        return list(Wrappers.<Role> lambdaQuery().eq(Role::getStatus, SystemConstants.NORMAL));
    }

    @Override
    @Transactional
    public void insertRole(Role role) {

        save(role);
        System.out.println(role.getId());
        if (role.getMenuIds() != null && role.getMenuIds().length > 0) insertRoleMenu(role);

    }

    @Override
    public void updateRole(Role role) {

        updateById(role);
        roleMenuService.deleteRoleMenuByRoleId(role.getId());
        insertRoleMenu(role);

    }

    @Override
    public List<Long> selectRoleIdByUserId(Long userId) {
        return getBaseMapper().selectRoleIdByUserId(userId);
    }

    private void insertRoleMenu(Role role) {

        List<RoleMenu> roleMenuList = Arrays.stream(role.getMenuIds())
                .map(menuId -> new RoleMenu(role.getId(), menuId))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);

    }

}

