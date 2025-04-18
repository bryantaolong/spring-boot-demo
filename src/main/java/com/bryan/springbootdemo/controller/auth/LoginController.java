package com.bryan.springbootdemo.controller.auth;

import com.bryan.springbootdemo.utils.ResponseUtils;
import com.bryan.springbootdemo.model.response.BaseResponse;
import com.bryan.springbootdemo.service.auth.TokenService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名: LoginController
 * 包名: com.bryan.springbootdemo.controller.auth
 * 描述: 登录控制器，用于处理用户登录和登出请求。
 * 作者: Bryan Long
 * 创建时间: 2025/1/2 - 11:33
 * 版本: v1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    // Token 服务，用于处理身份验证和令牌管理
    private final TokenService jwtTokenService;

    /**
     * 处理用户登录请求。
     * 接收用户名和密码，进行身份验证，并返回 JWT 令牌。
     *
     * @param username 用户名
     * @param password 密码
     * @return 包含 JWT 令牌和成功消息的响应
     */
    @PostMapping("/login")
    public BaseResponse<?> login(
            @RequestBody @NotBlank String username,
            @RequestBody @NotBlank String password) {
        log.info("LoginController.login: {}, {}", username, password);

        String token = jwtTokenService.authenticate(username, password);
        return ResponseUtils.success(token, "登录成功");
    }

    /**
     * 处理用户登出请求。
     * 接收 JWT 令牌，并清除该令牌以注销用户。
     *
     * @param token JWT 令牌
     */
    @PostMapping("/logout")
    public void logout(@RequestBody @NotBlank String token) {
        log.info("LoginController.logout: {}", token);
        jwtTokenService.clearToken(token);
    }
}
