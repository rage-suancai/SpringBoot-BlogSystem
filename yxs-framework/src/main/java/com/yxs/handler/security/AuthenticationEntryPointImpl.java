package com.yxs.handler.security;

import com.alibaba.fastjson.JSON;
import com.yxs.domain.ResponseResult;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YXS
 * @PackageName: com.yxs.handler.security
 * @ClassName: AuthenticationEntryPointImpl
 * @Desription:
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        e.printStackTrace();

        ResponseResult result = null;
        if (e instanceof BadCredentialsException)
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), e.getMessage());
        else if (e instanceof InsufficientAuthenticationException)
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        else
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");

        WebUtils.renderString(response, JSON.toJSONString(result)); // 响应到前端

    }

}
