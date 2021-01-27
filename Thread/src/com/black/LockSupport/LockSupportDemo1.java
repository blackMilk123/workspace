package com.black.LockSupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo1 {
    public static void main(String[] args) {
        System.out.println("开始");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("结束");
    }
}
