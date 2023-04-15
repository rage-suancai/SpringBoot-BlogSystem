package com.yxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.User;
import com.yxs.domain.entity.UserRole;
import com.yxs.domain.vo.PageVo;
import com.yxs.domain.vo.UserInfoVo;
import com.yxs.domain.vo.UserVo;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.handler.exception.SystemException;
import com.yxs.mapper.UserMapper;
import com.yxs.service.UserRoleService;
import com.yxs.service.UserService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: UserServiceImpl
 * @Desription:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRoleService userRoleService;

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
        if (nickNameExist(user.getNickName())) throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);

        String encodePassword = passwordEncoder.encode(user.getPassword()); // 加密
        user.setPassword(encodePassword);
        save(user); // 存入数据库
        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult selectUserPage(User user, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(user.getUserName()), User::getUserName, user.getUserName());
        queryWrapper.eq(StringUtils.hasText(user.getStatus()), User::getStatus, user.getStatus());
        queryWrapper.eq(StringUtils.hasText(user.getPhonenumber()), User::getPhonenumber, user.getPhonenumber());
        Page<User> page = new Page<>();
        page.setCurrent(pageNum); page.setSize(pageSize);
        page(page, queryWrapper);

        List<User> users = page.getRecords();
        List<UserVo> userVoList = users.stream()
                .map(u -> BeanCopyUtils.copyBean(u, UserVo.class))
                .collect(Collectors.toList());

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal()); pageVo.setRows(userVoList);
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public boolean checkUserNameUnique(String userName) {
        return count(Wrappers.<User>lambdaQuery().eq(User::getUserName, userName)) == 0;
    }
    @Override
    public boolean checkPhoneUnique(User user) {
        return count(Wrappers.<User>lambdaQuery().eq(User::getPhonenumber, user.getPhonenumber())) == 0;
    }
    @Override
    public boolean checkEmailUnique(User user) {
        return count(Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail())) == 0;
    }
    @Override
    @Transactional
    public ResponseResult addUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword())); // 密码加密处理
        save(user);
        if (user.getRoleIds() != null && user.getRoleIds().length > 0) insertUserRole(user);
        return ResponseResult.okResult();

    }

    @Override
    @Transactional
    public void updateUser(User user) {

        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(UserRole::getUserId, user.getId());
        userRoleService.remove(queryWrapper); // 删除用户与角色关联
        insertUserRole(user); updateById(user); // 新增/更新用户与角色管理信息

    }

    private void insertUserRole(User user) {

        List<UserRole> sysUserRoles = Arrays.stream(user.getRoleIds())
                .map(roleId -> new UserRole(user.getId(), roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(sysUserRoles);

    }

    private boolean userNameExist(String userName) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper) > 0;

    }
    private boolean nickNameExist(String nickName) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName, nickName);
        return count(queryWrapper) > 0;

    }

}
