package com.black.deadlock;

/**
 * 产生死锁的情况
 */
public class DeadLockTest2 {
    private  static  Object resourceA = new Object();
    private  static  Object resourceB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "获取资源A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("等待获取资源B");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "获取资源B");
                    }
                }
            }
        });

       Thread thread2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "获取资源B");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("等待获取资源A");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + "获取资源A");
                    }
                }
            }
        });


       thread1.start();
       thread2.start();
    }
}
