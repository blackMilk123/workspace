package com.imooc.sell;

import lombok.Data;

/**
 * 通过构造方法进行浅拷贝
 */
@Data
class Dog implements Cloneable{

    private String name;
    private  int age;
    private Foot foot;

    public Dog(Dog dog){
        this.age=dog.age;
        this.name=dog.name;
        this.foot=dog.foot;
    }
    public Dog(String name, int age,Foot foot){
        this.age=age;
        this.name=name;
        this.foot=foot;
    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +",address="+System.identityHashCode(this)+
                ", foot=" + foot +
                '}';
    }
}
@Data
class Foot {
    private String footNum;
    public Foot(String footNum) {
        this.footNum = footNum;
    }

    @Override
    public String toString() {
        return "Foot{" +
                "footNum=" +footNum +",address="+System.identityHashCode(this) +","+
                '}';
    }
}
public class ShallowCopy {
    public static void main(String[] args) {
        Foot foot = new Foot("四只脚");
        Dog dog1 = new Dog("旺财", 12, foot);
        Dog dog2 = new Dog(dog1);
        System.out.println("原对象---"+dog1);
        System.out.println("克隆后---"+dog2);
        dog1.setAge(18);
       foot.setFootNum("六只脚");

        System.out.println("修改后的原对象---"+dog1);
        System.out.println("修改后克隆对象---"+dog2);

    }

}
