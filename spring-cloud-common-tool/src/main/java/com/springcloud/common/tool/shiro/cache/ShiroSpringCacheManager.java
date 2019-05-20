package com.springcloud.common.tool.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 自定义cacheManage，使用spring实现
 *
 * @author jiangyf
 * @date 2018年7月21日 上午10:28:47
 */
public class ShiroSpringCacheManager implements CacheManager, Destroyable {

    private org.springframework.cache.CacheManager cacheManager;
    private RedisTemplate<byte[], byte[]> redisTemplate;

    public org.springframework.cache.CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public RedisTemplate<byte[], byte[]> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<byte[], byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        if (name == null) {
            return null;
        }
        return new ShiroSpringCache<K, V>(name, getCacheManager(), redisTemplate);
    }

    @Override
    public void destroy() throws Exception {
        cacheManager = null;
    }
}
