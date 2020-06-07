package com.black.synchronize;

class SynchronizedTest8{
    public synchronized void method1() {
        System.out.println(this.toString());
    }
}
public class SynchronizedTest7 extends SynchronizedTest8{
    @Override
    public synchronized void method1() {
        System.out.println(super.toString());
        System.out.println(this.toString());
        super.method1();
    }
    public static void main(String[] args) {
        SynchronizedTest7 test7 = new SynchronizedTest7();
        System.out.println(test7.toString());
        test7.method1();
    }
}

