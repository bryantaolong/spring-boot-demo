package com.bryan.springbootdemo.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Redis List 工具类
 * 提供常用的 Redis 列表操作方法，包括添加、删除、获取列表元素等功能。
 *
 * 使用 SLF4J 日志框架记录异常信息，方便调试和维护。
 *
 * 作者: Bryan Long
 * 创建时间: 2024/12/15
 * 版本: v1.0
 */
@Slf4j
@Component
public class RedisListUtil extends BaseRedisUtil {

    public RedisListUtil(RedisTemplate<Object, Object> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 向 Redis 列表左侧添加元素。
     *
     * @param key   列表键，不能为 null
     * @param value 要添加的值
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean leftPush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis leftPush 操作失败，key: {}, value: {}", key, value, e);
            return false;
        }
    }

    /**
     * 向 Redis 列表右侧添加元素。
     *
     * @param key   列表键，不能为 null
     * @param value 要添加的值
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean rightPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis rightPush 操作失败，key: {}, value: {}", key, value, e);
            return false;
        }
    }

    /**
     * 从 Redis 列表左侧弹出元素。
     *
     * @param key 列表键，不能为 null
     * @return 弹出的元素，若列表为空或键不存在返回 null
     */
    public Object leftPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("Redis leftPop 操作失败，key: {}", key, e);
            return null;
        }
    }

    /**
     * 从 Redis 列表右侧弹出元素。
     *
     * @param key 列表键，不能为 null
     * @return 弹出的元素，若列表为空或键不存在返回 null
     */
    public Object rightPop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("Redis rightPop 操作失败，key: {}", key, e);
            return null;
        }
    }

    /**
     * 获取 Redis 列表中指定范围的元素。
     *
     * @param key   列表键，不能为 null
     * @param start 起始索引（包含）
     * @param end   结束索引（包含）
     * @return 列表中的元素，若键不存在返回空列表
     */
    public List<Object> range(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("Redis range 操作失败，key: {}, start: {}, end: {}", key, start, end, e);
            return null;
        }
    }

    /**
     * 获取 Redis 列表的长度。
     *
     * @param key 列表键，不能为 null
     * @return 列表长度，若键不存在返回 0
     */
    public long size(String key) {
        try {
            Long size = redisTemplate.opsForList().size(key);
            return size != null ? size : 0;
        } catch (Exception e) {
            log.error("Redis size 操作失败，key: {}", key, e);
            return 0;
        }
    }

    /**
     * 根据索引从 Redis 列表中获取元素。
     *
     * @param key   列表键，不能为 null
     * @param index 元素索引
     * @return 索引对应的元素，若键不存在或索引超出范围返回 null
     */
    public Object index(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("Redis index 操作失败，key: {}, index: {}", key, index, e);
            return null;
        }
    }

    /**
     * 根据索引更新 Redis 列表中的元素。
     *
     * @param key   列表键，不能为 null
     * @param index 元素索引
     * @param value 新值
     * @return 操作成功返回 true，失败返回 false
     */
    public boolean set(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("Redis set 操作失败，key: {}, index: {}, value: {}", key, index, value, e);
            return false;
        }
    }

    /**
     * 删除 Redis 列表中指定数量的元素。
     *
     * @param key   列表键，不能为 null
     * @param count 要删除的元素数量
     * @param value 要删除的值
     * @return 删除的元素数量
     */
    public long remove(String key, long count, Object value) {
        try {
            Long removedCount = redisTemplate.opsForList().remove(key, count, value);
            return removedCount != null ? removedCount : 0;
        } catch (Exception e) {
            log.error("Redis remove 操作失败，key: {}, count: {}, value: {}", key, count, value, e);
            return 0;
        }
    }
}
