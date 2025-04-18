package com.bryan.springbootdemo.model.response;

import com.bryan.springbootdemo.model.enums.StatusCode;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: BaseResponse
 * Package: com.bryan.template.domain.response
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 19:00
 * Version: v1.0
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int code, T data) {
        this(code, "", data);
    }

    public BaseResponse(StatusCode statusCode) {
        this(statusCode.getCode(), statusCode.getMessage(), null);
    }
}
