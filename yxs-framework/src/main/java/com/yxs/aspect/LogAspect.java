package com.yxs.aspect;

import com.alibaba.fastjson.JSON;
import com.yxs.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YXS
 * @PackageName: com.yxs.annotation
 * @ClassName: LogAspect
 * @Desription:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.yxs.annotation.SystemLog)")
    public void pt() { }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object ret;
        try {

            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);

        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;

    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=======Start=======");
        log.info("URL            : {}", request.getRequestURL()); // 打印请求 URL
        log.info("BusinessName   : {}", systemLog.businessName()); // 打印描述信息
        log.info("HTTP Method    : {}", request.getMethod()); // 打印 Http method
        log.info("Class Method   : {}. {}", joinPoint.getSignature().getDeclaringTypeName(),
                                            joinPoint.getSignature().getName()); // 打印调用 controller 的全路径以及执行方法
        log.info("IP             : {}", request.getRemoteHost()); // 打印请求的 IP
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs())); // 打印请求入参

    }
    private void handleAfter(Object ret) {
        log.info("Response       : {}", JSON.toJSONString(ret)); // 打印出参
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog system = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return system;

    }

}
