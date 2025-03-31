package org.wenzhuo4657.webhook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: wenzhuo4657
 * @date: 2025/3/31
 * @description:
 */
@Configuration
public class MyThreadpool {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
       return new  ThreadPoolExecutor(
               1,
               2,
               10,
               TimeUnit.SECONDS,
               new ArrayBlockingQueue<Runnable>(10),
               Executors.defaultThreadFactory(),
               new ThreadPoolExecutor.AbortPolicy()
       );

    }

}
