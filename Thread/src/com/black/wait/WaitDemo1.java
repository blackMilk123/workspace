package com.black.wait;

public class WaitDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

//        //这里的调用将会抛出一个IllegalMonitorStateException一场
//        o.wait();
//
//        //这样的调用才是正确的，在调用之前要先获取o对象锁
//        //这里是主线程获取到了锁
//        //因此主线程也会被一直阻塞住，因为没有别的线程去唤醒他
//        synchronized (o){
//            o.wait();
//        }

        o.notify();
    }
}
