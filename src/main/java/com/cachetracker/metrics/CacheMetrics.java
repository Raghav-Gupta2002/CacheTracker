package com.cachetracker.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CacheMetrics {
    private final Counter cacheHits;
    private final Counter cacheMisses;

    public CacheMetrics(MeterRegistry registry) {
        this.cacheHits = registry.counter("cache_hits_total");
        this.cacheMisses = registry.counter("cache_misses_total");
    }

    public void recordHit() {
        cacheHits.increment();
    }

    public void recordMiss() {
        cacheMisses.increment();
    }
}
