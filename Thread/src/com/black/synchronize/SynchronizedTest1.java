package com.black.synchronize;




public  class SynchronizedTest1 {

    private static   int value = 0;
    public    void method1(){
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

    public   void method2(){
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
        final SynchronizedTest1 test = new SynchronizedTest1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();
    }
}
