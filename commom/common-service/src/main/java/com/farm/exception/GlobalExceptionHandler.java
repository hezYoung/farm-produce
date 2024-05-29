/**
 * 1. @ClassName GlobalExceptionHandler
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 14:10
 */
package com.farm.exception;

import com.farm.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null , 201,"出现了异常") ;
    }
    @ExceptionHandler(value = ZdyException.class)     // 处理自定义异常
    @ResponseBody
    public Result error(ZdyException exception) {
        exception.printStackTrace();
        return Result.build(null , exception.getResultCodeEnum()) ;
    }
}