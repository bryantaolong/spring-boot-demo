package com.bryan.springbootdemo.controller.auth;

import com.bryan.springbootdemo.model.response.BaseResponse;
import com.bryan.springbootdemo.service.auth.TokenService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名: RegisterController
 * 包名: com.bryan.springbootdemo.controller.auth
 * 描述: 注册控制器，用于处理用户注册请求。
 * 作者: Bryan Long
 * 创建时间: 2025/1/2 - 11:33
 * 版本: v1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterController {

    // Token 服务，用于处理身份验证和令牌管理
    private final TokenService jwtTokenService;

    /**
     * 处理用户注册请求。
     * 接收用户名和密码，注册新用户并返回适当的响应。
     *
     * @param username 用户名
     * @param password 密码
     * @return 注册操作的响应
     */
    @PostMapping("/register")
    public BaseResponse<?> register(
            @RequestBody @NotBlank String username,
            @RequestBody @NotBlank String password) {
        log.info("RegisterController.register: {}, {}", username, password);
        return null;
    }
}
