package com.bryan.springbootdemo.interceptor;

import com.bryan.springbootdemo.service.auth.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: JwtTokenInterceptor
 * Package: com.bryan.template.interceptor
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/30 - 9:00
 * Version: v1.0
 */
@Slf4j
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService jwtTokenService;

    /**
     * 在请求处理之前进行拦截
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  请求的处理器
     * @return true表示继续处理请求，false表示拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || token.isBlank()) {
            log.info("Token couldn't be null");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing");
            return false;
        }

        try {
            // 验证token
            String result = jwtTokenService.verifyToken(token);
            if (result == null) {
                log.info("Token verification failed");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return false;
            }

        } catch (Exception e) {
            log.error("Token verification failed: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token validation failed");
            return false;
        }

        // Token验证通过，继续处理请求
        return true;
    }
}

