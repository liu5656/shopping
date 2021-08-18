package com.dadalang.x.util.redis.cluster;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/16 9:27 上午
 * @desc
 */
@Data
@Component
@ConfigurationProperties("spring.redis.cluster")
public class RedisClusterConfigProperties {
    private List<String> nodes;
    private int maxRedirects;
}
