package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.entity.UserRole;
import com.yxs.mapper.UserRoleMapper;
import com.yxs.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: UserRoleServiceImpl
 * @Desription:
 * @date 2023/4/10 9:58
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {



}
