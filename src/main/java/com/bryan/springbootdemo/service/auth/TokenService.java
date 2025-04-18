package com.bryan.springbootdemo.service.auth;

/**
 * ClassName: TokenService
 * Package: com.bryan.template.service
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:53
 * Version: v1.0
 */
public interface TokenService {
    String authenticate(String username, String password);

    String refreshToken(String token);

    String verifyToken(String token);

    void clearToken(String token);
}
