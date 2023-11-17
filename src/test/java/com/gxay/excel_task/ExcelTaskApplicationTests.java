package com.gxay.excel_task;

import com.gxay.excel_task.pojo.Person;
import com.gxay.excel_task.service.PersonService;
import com.gxay.excel_task.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
//@RunWith(SpringRunner.class)
class ExcelTaskApplicationTests {

    @Autowired
    private PersonService personService;
    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() throws InterruptedException {
        //redisUtils.setList("a",personService.getAllPerson());
        //long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            return;
        }
        System.out.println(2222);
//        int totalRecords = 50000;
//        int recordsPerThread = 5000;
//        int numThreads = 10;
//        List<Person> list = new ArrayList<>();
//        // 创建一个CountDownLatch，计数器为10
//        CountDownLatch latch = new CountDownLatch(numThreads);
//
//        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
//
//        // 定义10个线程
//        for (int i = 0; i < numThreads; i++) {
//            int offset = i * recordsPerThread;
//            int limit = Math.min(recordsPerThread, totalRecords - offset);
//
//            executor.execute(() -> {
//                List<Person> result = personService.getPersonByPage(offset, limit);
//                // 在这里可以处理或合并每个线程的结果
//                list.addAll(result);
//                // 线程执行完毕，减少计数器
//                latch.countDown();
//            });
//        }
//
//        // 等待所有线程执行完毕
//        latch.await();
//
//        executor.shutdown();
//

//        List<Person> p = personService.getAllPerson();
//        long end = System.currentTimeMillis();
//        System.out.println("数据库耗时"+(end-start)+"豪秒");
//
//        Long start1 = System.currentTimeMillis();
//        List<Person> p1 = personService.getAllPerson();
//        long end1 = System.currentTimeMillis();
//        System.out.println("再次查询数据库耗时"+(end1-start1)+"豪秒");
    }

}
