package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.handler.exception.SystemException;
import com.yxs.mapper.UserMapper;
import com.yxs.service.UserService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.SecurityUtils;
import com.yxs.vo.UserInfoVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: UserServiceImpl
 * @Desription:
 * @date 2023/3/20 17:38
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {

        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);

    }

    @Override
    public ResponseResult updateUserInfo(User user) {

        updateById(user);
        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult register(User user) {

        if (!StringUtils.hasText(user.getUserName())) throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        if (!StringUtils.hasText(user.getPassword())) throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        if (!StringUtils.hasText(user.getEmail())) throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        if (!StringUtils.hasText(user.getNickName())) throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);

        if (userNameExist(user.getNickName())) throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST); // 对数据进行是否存在判断
        if (userNickExist(user.getNickName())) throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);

        String encodePassword = passwordEncoder.encode(user.getPassword()); // 加密
        user.setPassword(encodePassword);
        save(user); // 存入数据库
        return ResponseResult.okResult();

    }

    private boolean userNameExist(String userName) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper) > 0;

    }
    private boolean userNickExist(String nickName) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName, nickName);
        return count(queryWrapper) > 0;

    }

}
