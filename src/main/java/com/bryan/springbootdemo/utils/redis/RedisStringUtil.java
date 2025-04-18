package com.bryan.springbootdemo.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Redis String 工具类
 * 提供常用的 Redis 操作方法，包括存储键值对、设置过期时间、查询键是否存在等功能。
 *
 * 支持无过期时间和带过期时间的存储方式。
 * 使用 SLF4J 日志框架记录异常信息，方便调试和维护。
 *
 * 作者: Bryan Long
 * 创建时间: 2024/12/12 - 10:23
 * 版本: v1.2
 */
@Slf4j
@Component
public class RedisStringUtil extends BaseRedisUtil {

    public RedisStringUtil(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 在 Redis 中存储一个键值对（无过期时间）。
     *
     * @param key   键，不能为 null
     * @param value 值，可以为任意对象
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis set 操作失败，key: {}, value: {}", key, value, e);
            return false;
        }
    }

    /**
     * 在 Redis 中存储一个带有过期时间的键值对。
     *
     * @param key     键，不能为 null
     * @param value   值，可以为任意对象
     * @param seconds 过期时间（秒），必须大于 0
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean set(String key, Object value, long seconds) {
        try {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(seconds));
            return true;
        } catch (Exception e) {
            log.error("Redis setWithExpire 操作失败，key: {}, value: {}, seconds: {}", key, value, seconds, e);
            return false;
        }
    }

    /**
     * 为 Redis 中已存在的键设置过期时间。
     *
     * @param key     键，不能为 null
     * @param seconds 过期时间（秒），必须大于 0
     * @return 操作成功返回 true，若键不存在或设置失败返回 false
     */
    public boolean setExpire(String key, long seconds) {
        try {
            return Boolean.TRUE.equals(redisTemplate.expire(key, Duration.ofSeconds(seconds)));
        } catch (Exception e) {
            log.error("Redis setExpire 操作失败，key: {}, seconds: {}", key, seconds, e);
            return false;
        }
    }

    /**
     * 从 Redis 中获取与键对应的值。
     *
     * @param key 键，不能为 null
     * @return 键对应的值，若键不存在返回 null
     */
    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("Redis get 操作失败，key: {}", key, e);
            return null;
        }
    }

    /**
     * 从 Redis 中删除一个键。
     *
     * @param key 键，不能为 null
     * @return 若键删除成功返回 true，若键不存在返回 false
     */
    public boolean delete(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        } catch (Exception e) {
            log.error("Redis delete 操作失败，key: {}", key, e);
            return false;
        }
    }

    /**
     * 检查 Redis 中是否存在某个键。
     *
     * @param key 键，不能为 null
     * @return 若键存在返回 true，不存在返回 false
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error("Redis hasKey 操作失败，key: {}", key, e);
            return false;
        }
    }

}


