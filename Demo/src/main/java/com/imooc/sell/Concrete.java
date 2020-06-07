package com.imooc.sell;

interface interOne{
    int a = 8;
}
interface interTwo{
    int a = 12;
}
public class Concrete implements  interOne,interTwo {
    public static void main(String[] args) {
        int a= 1;
        System.out.println(a);
    }
}
