package com.springcloud.common.tool.shiro.cache;

import com.springcloud.common.tool.util.SerializableUtil;
import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义缓存，将数据存入到redis中
 *
 * @author jiangyf
 * @date 2018年7月21日 上午10:30:15
 */
public class ShiroSpringCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {
    private Cache cache;
    private CacheManager cacheManager;
    private RedisTemplate<byte[], byte[]> redisTemplate;
    /**
     * 缓存前缀
     */
    private String cacheKey;
    private static final String SHIRO_CACHE = "shiro_cache:";

    public ShiroSpringCache(String name, CacheManager cacheManager, RedisTemplate<byte[], byte[]> redisTemplate) {
        if (name == null || cacheManager == null) {
            throw new IllegalArgumentException("cacheManager or CacheName cannot be null.");
        }
        this.cache = cacheManager.getCache(name);
        this.cacheManager = cacheManager;
        this.cacheKey = SHIRO_CACHE + name + ":";
        this.redisTemplate = redisTemplate;
    }

    @Override
    public V get(K key) throws CacheException {
        if (key == null) {
            return null;
        }
//        ValueWrapper valueWrapper = cache.get(wrapperKey(key));
//        if (valueWrapper == null) {
//            return null;
//        }
//        return (V) valueWrapper.get();

        byte[] valueByte = redisTemplate.opsForValue().get(wrapperKey(key).getBytes());
        if (valueByte == null) {
            return null;
        }
        return (V) SerializableUtil.unserizlize(valueByte);
    }

    @Override
    public V put(K key, V value) throws CacheException {
//        cache.put(wrapperKey(key), value);
        redisTemplate.opsForValue().set(wrapperKey(key).getBytes(), SerializableUtil.serialize(value));
        return get(key);
    }

    @Override
    public V remove(K key) throws CacheException {
        V v = get(key);
        redisTemplate.delete(wrapperKey(key).getBytes());
//        cache.evict(wrapperKey(key));
        return v;
    }

    @Override
    public void clear() throws CacheException {
//        for(K key : keys()) {
//            redisTemplate.delete(wrapperKey(key).getBytes());
//        }
//        cache.clear();
    }

    @Override
    public int size() {
        return cacheManager.getCacheNames().size();
    }

    /**
     * 获取缓存中所的key值
     */
    @Override
    public Set<K> keys() {
        return (Set<K>) cacheManager.getCacheNames();
    }

    /**
     * 获取缓存中所有的values值
     */
    @Override
    public Collection<V> values() {
        return (Collection<V>) cache.get(cacheManager.getCacheNames()).get();
    }


    private String wrapperKey(Object k) {
        return this.cacheKey + k;
    }
}
