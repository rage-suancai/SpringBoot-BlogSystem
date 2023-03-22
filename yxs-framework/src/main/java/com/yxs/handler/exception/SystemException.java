package com.yxs.handler.exception;

import com.yxs.enums.AppHttpCodeEnum;

/**
 * @author YXS
 * @PackageName: com.yxs.exception
 * @ClassName: SystemException
 * @Desription:
 * @date 2023/3/20 14:35
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public SystemException(AppHttpCodeEnum httpCodeEnum) {

        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
