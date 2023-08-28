package com.lm.dianping.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SimpleRedisLock implements ILock {

    private StringRedisTemplate stringRedisTemplate;

    private String lockName;

    private static final String ID_PREFIX = UUID.randomUUID(true) + "-";

    private static final String LOCK_PREFIX = "lock_";

    public SimpleRedisLock(StringRedisTemplate stringRedisTemplate, String lockName) {
        this.lockName = lockName;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public boolean tryLock(long timeoutSec) {
         /*
          获取锁时存入线程标识
          解决分布式锁 **误删** 问题
         */
        
        // 获取线程标示
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 获取锁
        Boolean isSuccess = stringRedisTemplate.opsForValue()
                .setIfAbsent(LOCK_PREFIX + lockName, threadId, timeoutSec, TimeUnit.MINUTES);
//        return isSuccess;  // 可能会在自动拆箱过程中出现空指针
        return Boolean.TRUE.equals(isSuccess);
    }

    @Override
    public void unlock() {
        /*
         释放锁时先获取锁中的线程标识，判断是否与当前标识相同
         如果一致则释放锁，不一致则不释放锁；
         */

        String threadId = ID_PREFIX + Thread.currentThread().getId();
        String id = stringRedisTemplate.opsForValue().get(LOCK_PREFIX + lockName);
        if (threadId.equals(id)) {
            stringRedisTemplate.delete(LOCK_PREFIX + lockName);
        }
    }
}
