package com.lm.dianping.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 生成全局唯一ID（雪花算法）
 */
@Component
public class UIDWorker {

    // 开始时间戳
    private static final long BEGIN_TIMESTAMP = 1692213900;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Long nextId(String prefix){
        // 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;

        // 机器ID
        long machineId = 1;

        // 生成序列号(通过Redis自增生成序列)
        String nowDateTime = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        long increment = stringRedisTemplate.opsForValue().increment("icr" + prefix + ":" + nowDateTime);

        // 拼接并返回
        long id = timestamp << 22 | machineId << 12 | increment;

        return id;
    }
//    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 16, 19, 25);
//        long second = localDateTime.toEpochSecond(ZoneOffset.UTC);
//        System.out.println(second);
//    }
}
