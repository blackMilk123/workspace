package com.black.yield;

public class YieldDemo1 {
    public static void main(String[] args) {
        Thread one = new Thread(() -> {

            for (int i = 0; i < 20; i++) {
                if (i%5 == 0){
                    System.out.println(Thread.currentThread().getName()+"调用yield放弃了cpu执行权");
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread().getName()+"结束");
        },"线程1");
        Thread two = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                if (i%5 == 0){
                    System.out.println(Thread.currentThread().getName()+"调用yield放弃了cpu执行权");
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread().getName()+"结束");
        },"线程2");

        one.start();
        two.start();
    }
}
