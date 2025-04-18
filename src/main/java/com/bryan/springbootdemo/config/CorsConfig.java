package com.bryan.springbootdemo.config;

import com.bryan.springbootdemo.interceptor.JwtTokenInterceptor;
import com.bryan.springbootdemo.interceptor.UserRoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: CorsConfig
 * Package: com.bryan.template.config
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:46
 * Version: v1.0
 */
@Configuration
//@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public JwtTokenInterceptor tokenInterceptor() {
        return new JwtTokenInterceptor();
    }

    @Bean
    public UserRoleInterceptor roleInterceptor() {
        return new UserRoleInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/api/**") // 依然可以匹配全部路径
                .excludePathPatterns(
                        "/api/auth/login", // 登录
                        "/api/auth/register" // 注册
                );

        registry.addInterceptor(roleInterceptor())
                .addPathPatterns("/api/user/**") // 用户相关路径需要 `/api`
                .excludePathPatterns(
                        "/api/user/{id}",            // 用户详情
                        "/api/user/resetPassword"    // 重置密码
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有端点
                .allowedOriginPatterns("*") // 允许您的前端源
                .allowCredentials(true) // 允许携带凭证
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许必要的 HTTP 方法
                .allowedHeaders("*")
                .maxAge(1800); // 预检响应缓存 30 min
    }

}
