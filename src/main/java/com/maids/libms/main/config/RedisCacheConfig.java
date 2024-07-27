package com.maids.libms.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisCacheConfig {
    @Value("${application.cache.ttl.min}")
    long itemCacheTtlMinutes;
    @Value("${application.cache.ttl.min}")
    long pageCacheTtlMinutes;

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("itemCache",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .disableCachingNullValues()
                                .serializeKeysWith(RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(RedisSerializer.json()))
                                .entryTtl(Duration.ofMinutes(itemCacheTtlMinutes))
                )
                .withCacheConfiguration("pageCache",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .serializeKeysWith(RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(RedisSerializer.json()))
                                .entryTtl(Duration.ofMinutes(pageCacheTtlMinutes))
                )
                ;
    }
}
