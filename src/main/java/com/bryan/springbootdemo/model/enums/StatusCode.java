package com.bryan.springbootdemo.model.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * ClassName: StatusCode
 * Package: com.bryan.template.common.enums
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 19:00
 * Version: v1.0
 */
@Getter
@ToString
public enum StatusCode {

    SUCCESS(20000, "ok"),
    PARAMS_REQUEST(40000, "请求参数错误"),
    NO_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101,"无权限"),
    NOT_FOUND(40400, "请求资源不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;


    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
