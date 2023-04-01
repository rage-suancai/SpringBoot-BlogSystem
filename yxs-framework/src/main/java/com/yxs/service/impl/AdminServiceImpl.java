package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.LoginUser;
import com.yxs.domain.entity.Menu;
import com.yxs.domain.entity.User;
import com.yxs.domain.vo.AdminUserInfoVo;
import com.yxs.domain.vo.RoutersVo;
import com.yxs.domain.vo.UserInfoVo;
import com.yxs.mapper.AdminMapper;
import com.yxs.service.AdminService;
import com.yxs.service.MenuService;
import com.yxs.service.RoleService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.JwtUtil;
import com.yxs.utils.RedisCache;
import com.yxs.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: AdminServiceImpl
 * @Desription:
 * @date 2023/3/22 19:40
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, User> implements AdminService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult<RoutersVo> login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) throw new RuntimeException("您的用户名或密码错误");
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("login:" + userId, loginUser);

        Map<String, String> map = new HashMap<>(); // token封装 返回
        map.put("token", jwt);
        return ResponseResult.okResult(map);

    }

    @Override
    public ResponseResult<AdminUserInfoVo> getInfo() {

        LoginUser loginUser = SecurityUtils.getLoginUser(); // 获取当前登录的用户
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId()); // 根据用户id查询权限信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId()); // 根据用户id查询角色信息
        User user = loginUser.getUser(); // 获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);

    }

    @Override
    public ResponseResult<RoutersVo> getRouters() {

        Long userId = SecurityUtils.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);// 查询menuVo 结果是tree的形式
        return ResponseResult.okResult(new RoutersVo(menus)); // 封装数据返回

    }

    @Override
    public ResponseResult logout() {

        Long userId = SecurityUtils.getUserId(); // 获取当前登录的用户id
        redisCache.deleteObject("login:" + userId); // 删除redis中对应的值
        return ResponseResult.okResult();

    }

}
