package com.black;

public class join {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() ->{
                System.out.println("线程1");
               for (;;);
        });
        Thread two = new Thread(() ->{
            try {
                System.out.println("线程2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        two.start();
        one.start();


    }
}
