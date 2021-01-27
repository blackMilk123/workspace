package com.imooc.sell;

import lombok.Data;

import java.io.Serializable;

/**
 * 通过重写所有的clone方法进行深拷贝
 */
@Data
class Ear implements Cloneable,Serializable{

    private int earNum;
    private String earName;

    @Override
    public String toString() {
        return "Ear{" +
                "earNum=" + earNum +
                ", earName='" + earName + '\'' +",address="+System.identityHashCode(this)+
                '}';
    }

    public Ear(int earNum, String earName) {
        this.earNum = earNum;
        this.earName = earName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
@Data
class Cat implements Cloneable, Serializable {
    private  int age;
    private String name;
    private Ear ear;

    public Cat(int age, String name, Ear ear) {
        this.age = age;
        this.name = name;
        this.ear = ear;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Cat cloneCat =(Cat) super.clone();
        //对Ear进行克隆
        cloneCat.ear = (Ear) cloneCat.getEar().clone();
        return cloneCat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +",address="+System.identityHashCode(this)+
                ", ear=" + ear +
                '}';
    }

}
public class DepthCopy {
    public static void main(String[] args) throws CloneNotSupportedException {


        Ear ear = new Ear(2, "左耳朵");
        Cat cat = new Cat(18, "加菲猫", ear);
        Cat cloneCat = (Cat)cat.clone();
        System.out.println("原始的"+cat);

        System.out.println("克隆的"+cloneCat);
        //和之前一样改变ear的值
        ear.setEarName("右耳朵");
        ear.setEarNum(1);
        System.out.println("修改后的原对象"+cat);
        System.out.println("修改后克隆对象"+cloneCat);

    }

}

