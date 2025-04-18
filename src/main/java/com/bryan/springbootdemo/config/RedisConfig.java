package com.bryan.springbootdemo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;

/**
 * ClassName: RedisConfig
 * Package: com.bryan.template.config
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:52
 * Version: v1.0
 */
@Configuration
@EnableCaching // 开启缓存支持
@EnableTransactionManagement // 开启事务管理支持
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 设置 key 的序列化方式为 String
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 设置 value 的序列化方式为 JSON
        redisTemplate.setValueSerializer(new org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();// 初始化配置
        redisTemplate.setEnableTransactionSupport(true);// 开启事务支持

        return redisTemplate;
    }

    /**
     * 配置 RedisCacheManager，用于管理 Redis 缓存。
     * 该方法返回一个自定义配置的 RedisCacheManager 实例。
     *
     * @param connectionFactory Redis 连接工厂，用于创建 Redis 连接
     * @return 配置好的 RedisCacheManager 实例
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // 配置默认的缓存行为
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存的默认过期时间为 30 分钟
                .entryTtl(Duration.ofMinutes(30))
                // 设置缓存键的序列化方式为 String 序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置缓存值的序列化方式为 JSON 序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        // 创建并返回一个 RedisCacheManager 实例，使用自定义的默认配置
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config) // 设置全局默认缓存配置
                .build();
    }

}