package com.joji.taowu.user.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisSmsCache {
    private static final String SMS_CACHE_PREFIX = "sms:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void put(String phoneNumber, String verifyNumber) {
        String key = SMS_CACHE_PREFIX + phoneNumber;
        redisTemplate.opsForValue().set(key, verifyNumber, Duration.ofMinutes(1));
    }


    public String get(String phoneNumber) {
        String key = SMS_CACHE_PREFIX + phoneNumber;
        return redisTemplate.opsForValue().get(key);
    }

    public void remove(String phoneNumber) {
        String key = SMS_CACHE_PREFIX + phoneNumber;
        redisTemplate.delete(key);
    }
}
