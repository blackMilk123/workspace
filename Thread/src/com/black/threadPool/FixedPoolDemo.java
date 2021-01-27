package com.black.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedPoolDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"----"+ finalI);
            });
        }
    }
}
