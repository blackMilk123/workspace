package com.black.deadlock;

/**
 * 保证两个线程获取资源的顺序一致就可以避免死锁 因为此时任意一个线程获取资源A的时候,另一个线程都会阻塞不会去获取资源B
 */
public class DeadLockTest3 {
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
                    System.out.println(Thread.currentThread()+"等待获取资源B");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "获取资源B");
                    }
                }
            }
        });

        Thread thread2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "获取资源A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread()+"等待获取资源B");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "获取资源B");
                    }
                }
            }
        });


        thread1.start();
        thread2.start();
    }
}
