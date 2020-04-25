package com.aier.ihrm.common.entity;

/**
 * <p>Title: com.aier.ihrm.common.entity</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/18 16:28
 * Description: 公共的返回码
 */
public enum ResultCode {
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,10001,"操作失败！"),
    UNAUTHENTICATED(false,10002,"您还未登录！"),
    UNAUTHORISE(false,10003,"权限不足"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),


    //----用户操作返回码   2XXXXX----
    MOBILE_OR_PASSWORD_ERROR(false,20001,"用户名或密码错误！")
    ;


    boolean success;
    int code;
    String message;

    ResultCode(boolean success,int code,String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
