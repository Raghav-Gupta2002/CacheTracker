package com.cachetracker.service;

import com.cachetracker.annotations.TrackCache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable(value = "users", key = "#id")
    @TrackCache
    public String getUserById(Long id) {
        // Simulated DB call
        simulateSlowDatabaseCall(id);
        return "User_" + id;
    }

    private void simulateSlowDatabaseCall(Long id) {
        try {
            Thread.sleep(1000); // Simulate DB latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
