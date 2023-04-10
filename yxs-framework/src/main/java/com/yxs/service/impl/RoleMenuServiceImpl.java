package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.entity.RoleMenu;
import com.yxs.mapper.RoleMenuMapper;
import com.yxs.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: RoleMenuServiceImpl
 * @Desription:
 * @date 2023/4/10 9:27
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public void deleteRoleMenuByRoleId(Long id) {

        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, id);
        remove(queryWrapper);

    }

}
