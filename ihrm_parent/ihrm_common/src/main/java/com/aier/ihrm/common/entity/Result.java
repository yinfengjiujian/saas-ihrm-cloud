package com.aier.ihrm.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: com.aier.ihrm.common.entity</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/18 16:39
 * Description: No Description
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public Result(ResultCode resultCode) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
    }

    public Result(ResultCode resultCode, T data) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }

    public Result(boolean success, Integer code,String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR() {
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }
}
