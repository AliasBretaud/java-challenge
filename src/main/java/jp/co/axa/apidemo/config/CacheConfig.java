package jp.co.axa.apidemo.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * Configuration class for cache management of employees operations
 * @author Florian
 *
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
    public final static String EMPLOYEES_CACHE_NAME = "employees";
    public final static String EMPLOYEE_CACHE_NAME = "employee";

    @Bean
    public Cache cacheEmployees() {
        return new CaffeineCache(EMPLOYEES_CACHE_NAME, Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .build());
    }
    
    @Bean
    public Cache cacheEmployee() {
        return new CaffeineCache(EMPLOYEE_CACHE_NAME, Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .build());
    }
}