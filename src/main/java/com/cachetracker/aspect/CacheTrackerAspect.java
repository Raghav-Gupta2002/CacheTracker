package com.cachetracker.aspect;

import com.cachetracker.annotations.TrackCache;
import com.cachetracker.metrics.CacheMetrics;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import com.cachetracker.annotations.TrackCache;


@Aspect
@Component
@RequiredArgsConstructor
public class CacheTrackerAspect {

    private final CacheMetrics cacheMetrics;
    private final CacheManager cacheManager;

    @Around("@annotation(com.cachetracker.annotations.TrackCache)")
    public Object trackCacheUsage(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("🔥 TrackCache AOP triggered for: " + joinPoint.getSignature().toShortString());
        String cacheName = "users";
        Object key = joinPoint.getArgs()[0]; // assumes 1st arg is the cache key

        Cache cache = cacheManager.getCache(cacheName);
        Object cached = cache.get(key);

        boolean cacheHit = cached != null;
        if (cacheHit) {
            System.out.println("✅ Simulated Cache HIT");
            cacheMetrics.recordHit();
        } else {
            System.out.println("❌ Simulated Cache MISS");
            cacheMetrics.recordMiss();
        }

        return joinPoint.proceed();
    }

}
