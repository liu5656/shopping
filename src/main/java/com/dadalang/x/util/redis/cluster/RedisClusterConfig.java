package com.dadalang.x.util.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/15 5:52 下午
 * @desc
 */
@Configuration
public class RedisClusterConfig {

    @Autowired RedisClusterConfigProperties redisClusterConfigProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisClusterConfigProperties.getNodes());
        configuration.setMaxRedirects(redisClusterConfigProperties.getMaxRedirects());
//        configuration.setPassword("b123456");
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setStringSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
