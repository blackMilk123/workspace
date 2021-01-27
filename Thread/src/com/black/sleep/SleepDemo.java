package com.black.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread one = new Thread(() -> {
            lock.lock();
            System.out.println("线程A准备被Sleep");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程A被唤醒");
            lock.unlock();

        });
        Thread two = new Thread(() -> {
            lock.lock();
            System.out.println("线程B准备被Sleep");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程B被唤醒");
            lock.unlock();
        });

        one.start();
        two.start();

    }
}
