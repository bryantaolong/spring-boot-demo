package com.bryan.springbootdemo.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis Hash 工具类
 * 提供常用的 Redis 哈希操作方法，包括添加、删除、获取列表元素等功能。
 *
 * 使用 SLF4J 日志框架记录异常信息，方便调试和维护。
 *
 * 作者: Bryan Long
 * 创建时间: 2024/12/15
 * 版本: v1.0
 */
@Slf4j
@Component
public class RedisHashUtil extends BaseRedisUtil {

    public RedisHashUtil(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 在 Redis 的 Hash 中存储多个键值对。
     *
     * @param key   哈希的键，不能为 null
     * @param value 哈希的键值对，不能为 null
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean set(String key,  Map<?, ?> value) {
        try {
            redisTemplate.opsForHash().putAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis hSet 操作失败，key: {}, value: {}", key, value, e);
            return false;
        }
    }

    /**
     * 在 Redis 的 Hash 中存储一个键值对。
     *
     * @param key   哈希的键，不能为 null
     * @param value 哈希字段的值，可以为任意对象
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean set(String key, String hashKey,  Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            log.error("Redis hSet 操作失败，key: {}, hashKey: {}, value: {}", key, hashKey, value, e);
            return false;
        }
    }

    /**
     * 在 Redis 的 Hash 中获取某个字段的值。
     *
     * @param key     哈希的键，不能为 null
     * @param hashKey 哈希字段的键，不能为 null
     * @return 哈希字段的值，若字段不存在返回 null
     */
    public Object get(String key, String hashKey) {
        try {
            return redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            log.error("Redis hGet 操作失败，key: {}, hashKey: {}", key, hashKey, e);
            return null;
        }
    }

    /**
     * 从 Redis 的 Hash 中删除一个字段。
     *
     * @param key     哈希的键，不能为 null
     * @param hashKey 哈希字段的键，不能为 null
     * @return 若字段删除成功返回 true，若字段不存在或删除失败返回 false
     */
    public boolean delete(String key, String hashKey) {
        try {
            return Boolean.TRUE.equals(redisTemplate.opsForHash().delete(key, hashKey));
        } catch (Exception e) {
            log.error("Redis hDelete 操作失败，key: {}, hashKey: {}", key, hashKey, e);
            return false;
        }
    }

    /**
     * 检查 Redis 的 Hash 中是否存在某个字段。
     *
     * @param key     哈希的键，不能为 null
     * @param hashKey 哈希字段的键，不能为 null
     * @return 若字段存在返回 true，不存在返回 false
     */
    public boolean hasKey(String key, String hashKey) {
        try {
            return Boolean.TRUE.equals(redisTemplate.opsForHash().hasKey(key, hashKey));
        } catch (Exception e) {
            log.error("Redis hHasKey 操作失败，key: {}, hashKey: {}", key, hashKey, e);
            return false;
        }
    }

    /**
     * 获取 Redis 的 Hash 中所有字段和对应值。
     *
     * @param key 哈希的键，不能为 null
     * @return 哈希中的所有字段和值，若键不存在返回空 Map
     */
    public Map<Object, Object> getAll(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("Redis hGetAll 操作失败，key: {}", key, e);
            return Collections.emptyMap();
        }
    }

    /**
     * 获取 Redis 的 Hash 中所有字段。
     *
     * @param key 哈希的键，不能为 null
     * @return 哈希中的所有字段，若键不存在返回空 Set
     */
    public Set<Object> keys(String key) {
        try {
            return redisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            log.error("Redis hKeys 操作失败，key: {}", key, e);
            return Collections.emptySet();
        }
    }

    /**
     * 获取 Redis 的 Hash 中所有字段的值。
     *
     * @param key 哈希的键，不能为 null
     * @return 哈希中的所有字段的值，若键不存在返回空 List
     */
    public List<Object> values(String key) {
        try {
            return redisTemplate.opsForHash().values(key);
        } catch (Exception e) {
            log.error("Redis hValues 操作失败，key: {}", key, e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取 Redis 的 Hash 中字段数量。
     *
     * @param key 哈希的键，不能为 null
     * @return 哈希中的字段数量，若键不存在返回 0
     */
    public long size(String key) {
        try {
            return redisTemplate.opsForHash().size(key);
        } catch (Exception e) {
            log.error("Redis hSize 操作失败，key: {}", key, e);
            return 0L;
        }
    }
}
