package com.lm.dianping.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Reddision 配置
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // 配置
        Config config = new Config();
        // 单节点
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("lm12138");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
