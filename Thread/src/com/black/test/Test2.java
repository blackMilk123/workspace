package com.black.test;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替输出1A2B......
 */
public class Test2 {
static  Thread t1 = null;
   static Thread t2 = null;
    public static void main(String[] args) {


        char[] num = "123456789".toCharArray();
        char[] words = "ABCDEFGHI".toCharArray();


        t1 = new Thread(() ->{
           for (int i=0;i<num.length;i++){
               System.out.println(num[i]);
               LockSupport.park();
               LockSupport.unpark(t2);
           }
        },"t1");

        t2 = new Thread(() ->{
            for (int i=0;i<words.length;i++){
                System.out.println(words[i]);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        },"t2");
        t1.start();
        t2.start();

    }
}
