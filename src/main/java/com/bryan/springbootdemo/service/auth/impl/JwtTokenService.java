package com.bryan.springbootdemo.service.auth.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.bryan.springbootdemo.utils.redis.RedisStringUtil;
import com.bryan.springbootdemo.config.properties.JwtProperties;
import com.bryan.springbootdemo.model.entity.UserDetails;
import com.bryan.springbootdemo.service.auth.TokenService;
import com.bryan.springbootdemo.service.auth.UserDetailsService;
import com.bryan.springbootdemo.service.auth.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 类名: JwtTokenService
 * 包名: com.bryan.springbootdemo.service.auth.impl
 * 描述: 基于 JWT 的 Token 服务实现，提供认证、刷新、验证和清除 Token 的功能。
 * 作者: Bryan Long
 * 创建时间: 2024/12/29 - 18:53
 * 版本: v1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {

    private final JwtProperties jwtProperties;  // JWT 配置属性

    private final UserDetailsService userDetailsService;  // 用户详情服务

    private final UserRoleService userRoleService;  // 用户角色服务

    private final RedisStringUtil redisStringUtil;  // Redis 工具类

    /**
     * 认证用户并生成 Token。
     * 此方法使用缓存，缓存的 key 为 'username:' + 用户名。
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的 Token
     */
    @Override
    @Cacheable(value = "authenticatedUserCache", key = "'username:' + #username", unless = "#result == null")
    public String authenticate(String username, String password) {
        log.info("JwtTokenService.authenticateUser: {}", username);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!SecureUtil.md5(password).equals(userDetails.getPassword())) {
            log.error("Invalid username or password, username: {}", username);
            return null;
        }

        String roleName = userRoleService.loadRoleNameByRoleId(userDetails.getRoleId());

        if (jwtProperties.getAdminRoleName().equals(roleName)) {
            log.info("Administrator log in attempt, username: {}", userDetails.getUsername());
            return this.generateTokenForRole(username, roleName,
                    jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl());
        } else if (jwtProperties.getUserRoleName().equals(roleName)) {
            log.info("Normal user log in attempt, username: {}", userDetails.getUsername());
            return this.generateTokenForRole(username, roleName,
                    jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl());
        }

        return null;
    }

    /**
     * 刷新 Token。
     *
     * @param token 旧的 Token
     * @return 新的 Token
     */
    @Override
    public String refreshToken(String token) {
        log.info("JwtTokenService.refreshToken: {}", token);

        this.verifyToken(token);

        JWT jwt = JWTUtil.parseToken(token);
        String username = jwt.getPayload("username").toString();
        String role = jwt.getPayload("role").toString();

        String secretKey = this.getSecretKeyByRole(role);
        long ttl = jwtProperties.getUserRoleName().equals(role) ? jwtProperties.getUserTtl() : jwtProperties.getAdminTtl();

        return this.generateTokenForRole(username, role, secretKey, ttl);
    }

    /**
     * 验证 Token 的合法性和有效性。
     *
     * @param token 要验证的 Token
     * @return 角色名称
     */
    @Override
    public String verifyToken(String token) {
        log.info("JwtTokenService.verifyToken: {}", token);

        JWT jwt = JWTUtil.parseToken(token);
        String username = jwt.getPayload("username").toString();
        log.info("username: {}", username);

        if (!redisStringUtil.hasKey(username)) {
            log.error("Token is invalid or expired: {}", token);
            return null;
        }

        String role = jwt.getPayload("role").toString();
        String secretKey = this.getSecretKeyByRole(role);

        JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs256(secretKey.getBytes()));
        JWTValidator.of(token).validateDate();

        log.info("Token is valid, role: {}", role);
        return role;
    }

    /**
     * 清除 Token。
     *
     * @param token 要清除的 Token
     */
    @Override
    public void clearToken(String token) {
        log.info("JwtTokenService.clearToken: {}", token);

        JWT jwt = JWTUtil.parseToken(token);
        String id = jwt.getPayload("id").toString();

        redisStringUtil.delete(id);
    }

    /**
     * 根据用户角色生成 Token
     * @param username 用户名
     * @param roleName 角色名
     * @param secretKey 密钥
     * @param ttl 过期时间
     * @return Token
     */
    private String generateTokenForRole(String username, String roleName, String secretKey, long ttl) {
        clearToken(username);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", roleName);
        claims.put("expire_time", System.currentTimeMillis() + ttl);

        return JWTUtil.createToken(claims, secretKey.getBytes());
    }

    /**
     * 根据角色获取密码
     * @param role 角色
     * @return 密钥
     */
    private String getSecretKeyByRole(String role) {
        if (jwtProperties.getAdminRoleName().equals(role)) {
            return jwtProperties.getAdminSecretKey();
        } else if (jwtProperties.getUserRoleName().equals(role)) {
            return jwtProperties.getUserSecretKey();
        } else {
            throw new IllegalArgumentException("未知角色：" + role);
        }
    }
}
