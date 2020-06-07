package com.black.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachePoolDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        Executors.newScheduledThreadPool(1);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            int finalI = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"----"+ finalI);
            });
        }
        pool.shutdown();

        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println("耗时"+l);
    }
}
