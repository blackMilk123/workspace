package com.study.lambda;

import java.util.concurrent.TimeUnit;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-07-22 22:31
 **/
 class unsaftValue{
     private int value;
     public int getNext(){
         return value++;
     }
}
public class lambda01{


    public static void main(String[] args) {

        new Thread(() -> System.out.println("启动成功")).start();

        unsaftValue unsaftValue = new unsaftValue();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                if (i==5){
                    try {
                        Thread.sleep(5000);
//                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程1----"+unsaftValue.getNext());
            }
        }).start();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                if (i == 3){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程2----"+unsaftValue.getNext());
            }
        }).start();
    }
}
