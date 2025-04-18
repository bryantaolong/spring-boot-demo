package com.bryan.springbootdemo.utils.jwt;

import com.bryan.springbootdemo.config.properties.JwtProperties;
import lombok.RequiredArgsConstructor;

/**
 * ClassName: BaseJWTUtils
 * Package: com.bryan.springbootdemo.common.utils.jwt
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/4 - 18:21
 * Version: v1.0
 */
@RequiredArgsConstructor
public abstract class BaseJWTUtils {
    protected final JwtProperties jwtProperties;
}
