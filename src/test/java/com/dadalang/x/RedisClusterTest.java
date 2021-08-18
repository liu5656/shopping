package com.dadalang.x;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/16 9:33 上午
 * @desc
 */

@SpringBootTest
public class RedisClusterTest {

    @Autowired RedisTemplate<String, String> redisTemplate;

    @Test
    public void testSave() {
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForValue().set("key_" + i , "value_" + i);
        }
        Set<String> keys = redisTemplate.keys("key_*");
        for (String key : keys){
            String value = redisTemplate.opsForValue().get(key);
            System.out.println(value);
        }
    }

    @Test
    public void testNodea() {
        Jedis jedis = new Jedis("192.168.0.69", 7004);
        jedis.set("namex", "lisi");
        System.out.println(jedis.get("namex"));
    }


    @Test //java 连接 Redis 集群
    public void testjiqun(){
        //创建Set<HashAndPort> node
        //1.先创建一个集合，用来装我们集群的节点，包含IP+端口
        //尽可能的多写几个，防止有机器宕机了，然后我们无法连接上集群；
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("localhost",7004));
        nodes.add(new HostAndPort("localhost",7005));
        nodes.add(new HostAndPort("localhost",7006));
        nodes.add(new HostAndPort("localhost",7007));
        nodes.add(new HostAndPort("localhost",7008));
        nodes.add(new HostAndPort("localhost",7009));

        //创建JedisCluster对象
        //写完了集合之后，把他们封装到JedisCluster对象之中；相当于传参；
        //进行传递连接；
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //常规的指令都有，但是跨节点的操作就没有，比如没有 keys *
        System.out.print(jedisCluster.set("gender","sdfsd"));
        System.out.print(jedisCluster.set("age","200000"));

        System.out.print(jedisCluster.get("gender"));
        System.out.print(jedisCluster.get("age"));

    }

}
