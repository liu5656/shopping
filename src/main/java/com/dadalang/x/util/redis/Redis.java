package com.dadalang.x.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/18 2:46 下午
 * @desc
 */
@Component
public class Redis {
    @Autowired private JedisPool pool;

    public void set(String key, String value, int index, int seconds) {
        try(Jedis connect = pool.getResource()) {
            connect.select(index);
            connect.setex(key,seconds,value);
        }
    }
    public void set(String key, String value, int index) {
        set(key,value,index,-1);
    }

    public String get(String key, int index) {
        try(Jedis connect = pool.getResource()){
            connect.select(index);
            String result = connect.get(key);
            return result;
        }
    }
}
