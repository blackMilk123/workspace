package com.black.thredlocal;

public class ThreadLocalDemo1 {

    public static int value = 0;
   static ThreadLocal<Object> local = new ThreadLocal<>();
    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
////            new Thread(new Runnable() {
////                @Override
////                public void run() {
////                    System.out.println(Thread.currentThread().getName()+"---线程初始值:"+local.get());
////                    local.set("我是"+Thread.currentThread().getName());
////                    System.out.println(Thread.currentThread().getName()+"---线程修改值:"+local.get());
////                }
////            },"线程"+i).start();
////        }
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
        local.set("我是"+Thread.currentThread().getName());
        local.set("我是");

        System.out.println("主线程"+local.get());
    }

}
