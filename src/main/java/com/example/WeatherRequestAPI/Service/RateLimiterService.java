package com.example.WeatherRequestAPI.Service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isAllowed(String key, int limit, int windowSeconds) {
        // For now: global key, later you can change "rate:global" -> "rate:" + key
        String redisKey = "rate:global";

        Long count = redisTemplate.opsForValue().increment(redisKey);

        System.out.println("Count for key " + redisKey + ": " + count);

        if (count != null && count == 1) {
            redisTemplate.expire(redisKey, Duration.ofSeconds(windowSeconds));
        }

        return count != null && count <= limit;
    }
}
