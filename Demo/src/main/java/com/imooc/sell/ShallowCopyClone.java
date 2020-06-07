package com.imooc.sell;

import lombok.Data;

/**
 * 通过clone方法进行浅拷贝
 */
@Data
class Pig implements Cloneable{

    private String name;
    private  int age;
    private Foot foot;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public String toString() {
        return "Pig{" +
                "name='" + name + '\'' +
                ", age=" + age +",address="+System.identityHashCode(this)+
                ", foot=" + foot +
                '}';
    }
}
public class ShallowCopyClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Foot foot = new Foot("四只脚");
        Pig pig = new Pig();
        pig.setAge(18);
        pig.setFoot(foot);
        pig.setName("猪八戒");
        Pig clonePig = (Pig) pig.clone();
        System.out.println("原对象--"+pig);
        System.out.println("克隆后--"+clonePig);
        //现在改变pig中的属性
        pig.setAge(20);
        clonePig.setAge(80);
        pig.getFoot().setFootNum("一百只脚");
        System.out.println("修改后的原对象---"+pig);
        System.out.println("修改后克隆对象---"+clonePig);

    }
}
