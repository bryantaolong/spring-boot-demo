package com.bryan.springbootdemo.handler;

import com.bryan.springbootdemo.model.enums.StatusCode;
import com.bryan.springbootdemo.utils.ResponseUtils;
import com.bryan.springbootdemo.model.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 类名: GlobalExceptionHandler
 * 包名: com.bryan.template.common.handler
 * 描述: 全局异常处理器，用于统一处理和响应各种异常。
 * 作者: Bryan Long
 * 创建时间: 2024/12/30 - 11:13
 * 版本: v1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理运行时异常。
     * 记录异常日志并返回系统错误响应。
     *
     * @param e 运行时异常
     * @return 包含系统错误代码和消息的响应
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(final RuntimeException e) {
        log.error("运行时异常: ", e);
        return ResponseUtils.error(StatusCode.SYSTEM_ERROR.getCode(), StatusCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 处理约束违反异常。
     * 记录异常日志并返回参数校验错误的响应。
     *
     * @param e 约束违反异常
     * @return 包含参数请求错误代码和异常消息的响应
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> constraintViolationExceptionHandler(final ConstraintViolationException e) {
        log.error("约束违反异常: ", e);
        return ResponseUtils.error(StatusCode.PARAMS_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 处理参数绑定异常。
     * 记录异常日志并返回第一个校验错误信息的响应。
     *
     * @param e 参数绑定异常
     * @return 包含参数请求错误代码和第一个校验错误信息的响应
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse<?> bindExceptionHandler(final BindException e) {
        log.error("参数绑定异常: ", e);
        String message = e.getAllErrors().get(0).getDefaultMessage(); // 获取第一个校验错误信息
        return ResponseUtils.error(StatusCode.PARAMS_REQUEST.getCode(), message);
    }

    /**
     * 处理 JSON 解析异常。
     * 记录异常日志并返回请求体无效或参数错误的响应。
     *
     * @param e JSON 解析异常
     * @return 包含参数请求错误代码和无效请求体或参数的消息的响应
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse<?> httpMessageNotReadableExceptionHandler(final HttpMessageNotReadableException e) {
        log.error("JSON 解析异常: ", e);
        return ResponseUtils.error(StatusCode.PARAMS_REQUEST.getCode(), "请求体无效或参数错误");
    }

    /**
     * 处理空指针异常。
     * 记录异常日志并返回系统错误响应。
     *
     * @param e 空指针异常
     * @return 包含系统错误代码和消息的响应
     */
    @ExceptionHandler(NullPointerException.class)
    public BaseResponse<?> nullPointerExceptionHandler(final NullPointerException e) {
        log.error("空指针异常: ", e);
        return ResponseUtils.error(StatusCode.SYSTEM_ERROR.getCode(), StatusCode.SYSTEM_ERROR.getMessage());
    }
}
