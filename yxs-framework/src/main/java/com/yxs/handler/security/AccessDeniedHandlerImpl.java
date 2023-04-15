package com.yxs.handler.security;

import com.alibaba.fastjson.JSON;
import com.yxs.domain.ResponseResult;
import com.yxs.enums.AppHttpCodeEnum;
import com.yxs.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YXS
 * @PackageName: com.yxs.handler.security
 * @ClassName: AccessDeniedHandlerImpl
 * @Desription:
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        e.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);

        WebUtils.renderString(response, JSON.toJSONString(result)); // 响应到前端

    }

}
