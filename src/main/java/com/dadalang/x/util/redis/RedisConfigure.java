package com.dadalang.x.util.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/18 2:16 下午
 * @desc
 */
@Configuration
public class RedisConfigure {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long waitMills;



    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(waitMills);
        config.setBlockWhenExhausted(true);             // 连接耗尽时是否阻塞，false报异常，true阻塞直到超时。
        config.setJmxEnabled(true);                     // 启用jsm管理功能，默认true
        JedisPool pool = new JedisPool(config, host, port, timeout, password);
        return pool;
    }
}
