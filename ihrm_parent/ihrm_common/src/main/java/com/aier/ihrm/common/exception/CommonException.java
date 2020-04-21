package com.aier.ihrm.common.exception;

import com.aier.ihrm.common.entity.ResultCode;
import lombok.Getter;

/**
 * <p>Title: com.aier.ihrm.common.exception</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/20 23:25
 * Description: No Description
 */
@Getter
public class CommonException extends Exception {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
