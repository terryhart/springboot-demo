package com.bbsuper.nev.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import com.google.common.collect.Sets;
/**
 * redis缓存配置类
 * @author liwei
 * @date: 2018年8月21日 下午3:30:36
 *
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport{

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
				//.entryTtl(Duration.ofHours(12)); // 设置缓存有效期12小时
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).initialCacheNames(Sets.newHashSet("userInfo","city","vehicleType")).build();
	}

}
