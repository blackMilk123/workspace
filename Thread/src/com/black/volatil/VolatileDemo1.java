package com.black.volatil;

public class VolatileDemo1 {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        final VolatileDemo1 test = new VolatileDemo1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.setValue(1);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(test.getValue());  ;
            }
        }).start();
    }

}
