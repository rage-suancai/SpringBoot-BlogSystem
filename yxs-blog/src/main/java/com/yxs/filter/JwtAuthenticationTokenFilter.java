package com.yxs.filter;

import com.alibaba.fastjson.JSON;
import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.LoginUser;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.utils.JwtUtil;
import com.yxs.utils.RedisCache;
import com.yxs.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author YXS
 * @PackageName: com.yxs.filter
 * @ClassName: JwtAuthenticationTokenFilter
 * @Desription:
 * @date 2023/3/20 10:44
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token"); // 请求头token
        if (!StringUtils.hasText(token)) {

            filterChain.doFilter(request, response); // 直接放行
            return;

        }

        Claims claims = null; // 解析获取userid
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {

            e.printStackTrace(); // token超时 token非法 响应前端重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;

        }

        String userId = claims.getSubject();
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId); // redis获取用户信息
        if (Objects.isNull(loginUser)) {

            // 过期 重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;

        }

        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }

}
