package com.bryan.springbootdemo.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bryan.springbootdemo.config.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class Auth0JwtUtils extends BaseJWTUtils {

    public Auth0JwtUtils(JwtProperties jwtProperties) {
        super(jwtProperties);
    }

    /**
     * 创建JWT Token
     *
     * @param payload  负载数据
     * @param roleName 角色名称（admin/user）
     * @return 生成的JWT Token
     */
    public String createToken(Map<String, Object> payload, String roleName) {
        String secretKey;
        long ttl;

        if (jwtProperties.getAdminRoleName().equals(roleName)) {
            secretKey = jwtProperties.getAdminSecretKey();
            ttl = jwtProperties.getAdminTtl();
        } else if (jwtProperties.getUserRoleName().equals(roleName)) {
            secretKey = jwtProperties.getUserSecretKey();
            ttl = jwtProperties.getUserTtl();
        } else {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ttl);

        return JWT.create()
                .withPayload(payload)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    /**
     * 解析JWT Token
     *
     * @param token    JWT Token
     * @param roleName 角色名称（admin/user）
     * @return 解析结果
     */
    public Map<String, Claim> parseToken(String token, String roleName) {
        String secretKey;

        if (jwtProperties.getAdminRoleName().equals(roleName)) {
            secretKey = jwtProperties.getAdminSecretKey();
        } else if (jwtProperties.getUserRoleName().equals(roleName)) {
            secretKey = jwtProperties.getUserSecretKey();
        } else {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaims();
        } catch (JWTVerificationException e) {
            log.error("Token解析失败: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid token");
        }
    }

    /**
     * 验证JWT Token是否有效
     *
     * @param token    JWT Token
     * @param roleName 角色名称（admin/user）
     * @return 验证结果
     */
    public boolean verify(String token, String roleName) {
        try {
            return this.parseToken(token, roleName) != null;
        } catch (Exception e) {
            log.error("Token验证失败: {}", e.getMessage());
            return false;
        }
    }
}
