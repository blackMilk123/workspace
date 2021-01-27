package com.black.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SinglePoolDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"----"+ finalI);
            });
        }
    }
}
