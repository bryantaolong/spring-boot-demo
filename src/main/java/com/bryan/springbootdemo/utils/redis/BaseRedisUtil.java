package com.bryan.springbootdemo.utils.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * ClassName: BaseRedisUtil
 * Package: com.bryan.toyouth.common.utils.redis
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/15 - 10:09
 * Version: v1.0
 */
@RequiredArgsConstructor
public abstract class BaseRedisUtil {
    protected final RedisTemplate<Object, Object> redisTemplate;
}
