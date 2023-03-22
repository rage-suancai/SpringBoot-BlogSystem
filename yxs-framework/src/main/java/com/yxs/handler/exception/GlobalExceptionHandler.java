package com.yxs.handler.exception;

import com.yxs.domain.ResponseResult;
import com.yxs.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YXS
 * @PackageName: com.yxs.exception
 * @ClassName: GlobalExceptionHandler
 * @Desription:
 * @date 2023/3/20 14:38
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {

        log.error("出现了异常 {}", e);
        return ResponseResult.errorResult(e.getCode(), e.getMsg());

    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {

        log.error("出现了异常 {}" , e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, e.getMessage());

    }

}
