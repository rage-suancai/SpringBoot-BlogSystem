package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxs.constants.SystemConstants;
import com.yxs.domain.entity.LoginUser;
import com.yxs.domain.entity.User;
import com.yxs.mapper.MenuMapper;
import com.yxs.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: UserDetailServiceImpl
 * @Desription:
 * @date 2023/3/20 9:10
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String u) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getUserName, u); // 用户名查询用户信息
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) throw new RuntimeException("用户不存在"); // 是否查到 如果没查到抛出异常

        if (user.getType().equals(SystemConstants.ADMAIN)) { // 返回用户信息
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user, list);
        }
        return new LoginUser(user, null);

    }

}
