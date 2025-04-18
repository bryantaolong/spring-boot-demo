package com.bryan.springbootdemo.interceptor;

import com.bryan.springbootdemo.config.properties.JwtProperties;
import com.bryan.springbootdemo.service.auth.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: UserRoleInterceptor
 * Package: com.bryan.template.interceptor
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/30 - 9:00
 * Version: v1.0
 */
@Slf4j
@Component
public class UserRoleInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private TokenService jwtTokenService;


    /**
     * 在请求处理之前进行角色验证
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  请求的处理器
     * @return true表示继续处理请求，false表示拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 token 并解析出角色
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.info("Token couldn't be null");
            return false;
        }

        // 获取 token 中的角色信息
        String role = jwtTokenService.verifyToken(token);

        // 判断是否为管理员角色，其他角色无法访问/admin/**接口
        if (role != null && role.equalsIgnoreCase(jwtProperties.getAdminRoleName())) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            log.info("User doesn't have admin role");
            return false;
        }
    }
}
