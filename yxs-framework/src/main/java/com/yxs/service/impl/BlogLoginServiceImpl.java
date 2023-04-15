package com.yxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.LoginUser;
import com.yxs.domain.entity.User;
import com.yxs.domain.vo.BlogUserLoginVo;
import com.yxs.domain.vo.UserInfoVo;
import com.yxs.mapper.BlogLoginMapper;
import com.yxs.service.BlogLoginService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.JwtUtil;
import com.yxs.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author YXS
 * @PackageName: com.yxs.service.impl
 * @ClassName: UserServiceImpl
 * @Desription:
 */
@Service("blogLoginService")
public class BlogLoginServiceImpl extends ServiceImpl<BlogLoginMapper, User> implements BlogLoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) throw new RuntimeException("您的用户名或密码错误"); // 是否认证通过
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal(); // 获取userid 生成token
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("bloglogin:" + userId, loginUser); // 用户信息存进redis

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class); // token和userinfo封装 返回
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo); // User转换UserInfoVo
        return ResponseResult.okResult(vo);

    }

    @Override
    public ResponseResult logout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 获取解析token userid
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        Long userId = loginUser.getUser().getId(); // 获取userid
        redisCache.deleteObject("bloglogin:" + userId); // 删除redis的信息
        return ResponseResult.okResult();

    }

}
