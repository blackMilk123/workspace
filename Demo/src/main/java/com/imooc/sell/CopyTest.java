package com.imooc.sell;

class Two{
    int a =1;
    int b =2;

    @Override
    public String toString() {
        return "Two{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}


class One{
    public  int a;
    public  Two two;

    @Override
    public String toString() {
        return "One{" +
                "a=" + a +
                ", two=" + two +
                '}';
    }

    public  void swap(int a, Two  two){
       this.a=a;
        two.a=18;
        two.b=19;

        System.out.println("swap中的a="+a+"----"+two);
    }
}


public class CopyTest {
    public static void main(String[] args) {

        One one = new One();
        System.out.println(one);
        Two two = new Two();
        System.out.println("初始的two"+two);
        two.a=12;
        two.b=13;
        System.out.println("赋值后的"+two);
        one.swap(1,two);
        System.out.println("交换过的"+one);
        System.out.println("交换过的"+two);
    }
}
