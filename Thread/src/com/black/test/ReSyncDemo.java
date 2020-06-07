package com.black.test;

import java.util.concurrent.TimeUnit;

/**
 * 测试可重入锁
 */
public class ReSyncDemo {
    public synchronized void m1(){
        System.out.println(" I am M1");
        try {
            m2();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void m2(){
        System.out.println("I am M2");
    }

    public static void main(String[] args) {
        ReSyncDemo reSync = new ReSyncDemo();
        new Thread(() -> {
            reSync.m1();
        }).start();

        new Thread(() -> {
            reSync.m2();
        }).start();
    }
}
