package com.example.maven.springbootlearn.redis_demo.redis_conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import lombok.var;

@Configuration
@EnableCaching
@ConditionalOnProperty("spring.redis.jedis.pool.max-active")
public class RedisConfig {
	@Autowired
	CacheProperties cacheProperties;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	@Bean
	public RedisCacheConfiguration determineConfiguration() {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

		// 设置redis中值的序列化方式，方便redisclient可读
		config = config.serializeValuesWith(SerializationPair.fromSerializer(valueSerializer()));
		Redis redisProperties = this.cacheProperties.getRedis();
		if (redisProperties.getTimeToLive() != null) {
			config = config.entryTtl(redisProperties.getTimeToLive());
		}
		if (redisProperties.getKeyPrefix() != null) {
			config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
		}
		if (!redisProperties.isCacheNullValues()) {
			config = config.disableCachingNullValues();
		}
		if (!redisProperties.isUseKeyPrefix()) {
			config = config.disableKeyPrefix();
		}

		initRedisTemplat();
		return config;
	}

	private void initRedisTemplat() {

		var keySer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(keySer);
		redisTemplate.setHashKeySerializer(keySer);

		redisTemplate.setValueSerializer(valueSerializer());
		redisTemplate.setHashValueSerializer(valueSerializer());
	}

	/**
	 * 使用Jackson序列化器
	 * 
	 * @return
	 */
	private RedisSerializer<Object> valueSerializer() {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
				JsonTypeInfo.As.PROPERTY);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		return jackson2JsonRedisSerializer;
	}

}
