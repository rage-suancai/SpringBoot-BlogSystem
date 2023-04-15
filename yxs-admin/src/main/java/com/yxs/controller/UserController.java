package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.ChangeRoleStatusDto;
import com.yxs.domain.entity.Role;
import com.yxs.domain.entity.User;
import com.yxs.domain.vo.UserInfoAndRoleIdsVo;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.handler.exception.SystemException;
import com.yxs.service.CategoryService;
import com.yxs.service.RoleService;
import com.yxs.service.UserService;
import com.yxs.utils.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: UserController
 * @Desription:
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult listUser(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserPage(user, pageNum, pageSize);
    }

    @GetMapping(value = "/{userId}")
    public ResponseResult getUserInfoAndRoleIds(@PathVariable(value = "userId") Long userId) {

        List<Role> roles = roleService.selectRoleAll();
        User user = userService.getById(userId);
        List<Long> roleIds = roleService.selectRoleIdByUserId(userId); // 当前用户所具有的角色id列表
        return ResponseResult.okResult(new UserInfoAndRoleIdsVo(user, roles, roleIds));

    }

    @PostMapping
    public ResponseResult addUser(@RequestBody User user) {

        if (!StringUtils.hasText(user.getUserName()))
            throw  new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        if (!userService.checkUserNameUnique(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        if(!userService.checkPhoneUnique(user))
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        if(!userService.checkEmailUnique(user))
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        return userService.addUser(user);

    }

    @PutMapping
    public ResponseResult editUser(@RequestBody User user) {

        userService.updateUser(user);
        return ResponseResult.okResult();

    }

    @DeleteMapping(value = "/{userIds}")
    public ResponseResult removeUser(@PathVariable List<Long> userIds) {

        if (userIds.contains(SecurityUtils.getUserId()))
            return ResponseResult.errorResult(500, "不能删除当前正在使用的用户");
        userService.removeByIds(userIds);
        return ResponseResult.okResult();

    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto roleStatusDto) {

        User user = new User();
        user.setId(roleStatusDto.getRoleId()); user.setStatus(roleStatusDto.getStatus());
        return ResponseResult.okResult(userService.updateById(user));

    }

}
