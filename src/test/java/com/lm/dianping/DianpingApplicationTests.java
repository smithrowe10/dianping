package com.lm.dianping;

import com.lm.dianping.utils.UIDWorker;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DianpingApplicationTests {
    @Resource
    private UIDWorker uidWorker;
    @Test
    void testIdWorker() {
        Long nextId = uidWorker.nextId("order");
        System.out.println(nextId);
    }

}
