package com.tingyu.venus.common;

/**
 * 存放业务代码
 *
 * @Author essionshy
 * @Create 2020/10/21 16:07
 * @Version venus-server
 */
public enum BizCode {

    USER_NOT_LOGIN(10000,"用户未登录"),
    TOKEN_EXPIRED(10003,"登录已过期，请重新登录"),
    USER_NOT_EXISTS(10001,"用户不存在"),
    USER_EXISTS(10003,"用户已经存在"),
    PASSWORD_ERROR(10002,"密码不正确");

    private int code;

    private String message;

    BizCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
