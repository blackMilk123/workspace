package com.black.synchronize;


/**
 * 对方法进行加锁
 */
public class SynchronizedTest3 {

    private   int value = 0;
    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName() +"---Method 1 start");
        try {
            value++;
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() +"---Method 1 execute---value:"+value);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"---Method 1 end");
    }

    public synchronized  void method2(){
        System.out.println(Thread.currentThread().getName() +"---Method 2 start");
        try {
            value++;
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +"---Method 2 execute---value:"+value);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"---Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest3 test = new SynchronizedTest3();
        final SynchronizedTest3 test2 = new SynchronizedTest3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test2.method1();
            }
        }).start();
    }
}

