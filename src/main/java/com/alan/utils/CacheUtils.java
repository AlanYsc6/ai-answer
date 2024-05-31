package com.alan.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @Author Alan
 * @Date 2024/5/31 17:18
 * @Description 本地缓存工具类
 */
public class CacheUtils {
    //AI结果缓存
    private static final Cache<String, String> answerCacheMap =
            Caffeine.newBuilder().initialCapacity(1024)
                    // 缓存5分钟移除
                    .expireAfterAccess(5L, TimeUnit.MINUTES)
                    .build();

    public static String get(String key) {
        return answerCacheMap.getIfPresent(key);
    }

    public static void put(String key, String value) {
        answerCacheMap.put(key, value);
    }

    public static void clear() {
        answerCacheMap.invalidateAll();
    }
}
