package com.black.wait;

public class WaitDemo2 {

    public static void main(String[] args) throws InterruptedException {
        final Object resource =    new Object();

        Thread threadA =   new Thread(() ->{
            synchronized (resource){
                System.out.println("线程A获取了锁");
                try {
                    System.out.println("线程A开始wait");
                    resource.wait();
                    System.out.println("线程A结束wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread threadB =     new Thread(() ->{
            synchronized (resource){
                System.out.println("线程B获取了锁");
                try {
                    System.out.println("线程B开始wait");
                    resource.wait();
                    System.out.println("线程B结束wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread threadC =     new Thread(() ->{

            synchronized (resource){
                resource.notifyAll();
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 函数结束");
    }
}
