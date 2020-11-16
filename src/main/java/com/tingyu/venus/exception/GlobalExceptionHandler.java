package com.tingyu.venus.exception;

import com.tingyu.venus.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/11 0:26
 * @Version venus-server
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  {
    /**
     * 处理自定义异常 ResultException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ResultException.class})
    public CommonResult handleResultException(ResultException e) {
        return  CommonResult.error().code(e.getCode()).message(e.getMessage());
    }


    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return  CommonResult.error().code(4000).message( "参数校验失败").data("errorMap",errorMap);
    }
}
