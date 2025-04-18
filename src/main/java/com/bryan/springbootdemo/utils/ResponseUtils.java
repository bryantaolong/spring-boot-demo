package com.bryan.springbootdemo.utils;

import com.bryan.springbootdemo.model.enums.StatusCode;
import com.bryan.springbootdemo.model.response.BaseResponse;

/**
 * ClassName: ResponseUtils
 * Package: com.bryan.template.common.utils
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 19:01
 * Version: v1.0
 */
public class ResponseUtils {

    /**
     * 成功
     *
     * @param data 数据
     * @return 响应
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, "ok", data);
    }

    /**
     * 成功
     *
     * @param msg 消息
     * @return 响应
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> success(String msg) {
        return new BaseResponse<>(0, msg, null);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param msg 消息
     * @return 响应
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> success(T data, String msg) {
        return new BaseResponse<>(0, msg, data);
    }

    /**
     * 失败
     *
     * @param statusCode 错误码
     * @return 响应
     */
    public static BaseResponse<?> error(StatusCode statusCode) {
        return new BaseResponse<>(statusCode);
    }

    /**
     * 失败
     *
     * @param code 错误码
     * @param msg 消息
     * @return 响应
     */
    public static BaseResponse<?> error(int code, String msg) {
        return new BaseResponse<>(code, msg, null);
    }

    /**
     * 失败
     *
     * @param statusCode 错误码
     * @param msg 消息
     * @return 响应
     */
    public static BaseResponse<?> error(StatusCode statusCode, String msg) {
        return new BaseResponse<>(statusCode.getCode(), msg, null);
    }
}

