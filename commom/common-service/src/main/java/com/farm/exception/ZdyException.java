/**
 * 1. @ClassName ZdyException
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 14:11
 */
package com.farm.exception;

import com.farm.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class ZdyException extends RuntimeException{
    private Integer code ;          // 错误状态码
    private String message ;        // 错误消息

    private ResultCodeEnum resultCodeEnum ;     // 封装错误状态码和错误消息

    public ZdyException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public ZdyException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }


}

