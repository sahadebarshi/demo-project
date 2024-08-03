package com.example.demo.config;

import com.example.demo.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.cache.CacheManager;
import javax.cache.Caching;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@AutoConfigureBefore(value = {DbConfig.class})
@Slf4j
public class CacheConfig {

    private CacheManager cacheManager;

    @Bean
    public org.springframework.cache.CacheManager cacheManager()
    {
        cacheManager = getInMemCacheManager();
        return new JCacheCacheManager(cacheManager);
    }

    public CacheManager getInMemCacheManager()
    {
        CacheConfiguration<Object, Object> cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class,
                Object.class, ResourcePoolsBuilder.heap(1000))
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(15)))
                .build();

        Map<String, CacheConfiguration<?, ?>> caches = new HashMap<>();
        caches.put(Product.class.getName(), cacheConfig);
        EhcacheCachingProvider provider = (EhcacheCachingProvider)Caching.getCachingProvider();
        DefaultConfiguration configuration = new DefaultConfiguration(caches, provider.getDefaultClassLoader());

        return provider.getCacheManager(provider.getDefaultURI(), configuration);
    }

    @PreDestroy
    public void destroyCache()
    {
        log.info("Closing cache manager");
        cacheManager.close();
    }

}
