package com.black.volatil;

public class VolatileDemo2 {
    public static volatile  int value = 0;
    public void inc(){
        value++;
    }
    public static void main(String[] args) {
        VolatileDemo2 test = new VolatileDemo2();
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        value++;
                    }
                }
            }).start();
        }

        System.out.println(test.value);
    }

    }

