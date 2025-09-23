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
        String redisKey = "rate:global";

        Long count = redisTemplate.opsForValue().increment(redisKey);

        if (count == 1 && count == 1L) {
            redisTemplate.expire(
                    redisKey, Duration.ofSeconds(windowSeconds)
            );
        }

        return count != null && count <= limit;
    }
}
