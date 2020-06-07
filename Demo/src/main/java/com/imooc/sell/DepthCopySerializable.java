package com.imooc.sell;

import java.io.*;

/**
 * 通过序列化进行深拷贝
 */
public class DepthCopySerializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Ear ear = new Ear(2, "左耳");
        Cat cat = new Cat(15, "加菲猫", ear);
        ByteArrayOutputStream BO = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(BO);
        outputStream.writeObject(cat);
        outputStream.flush();
        ObjectInputStream inputStream=new ObjectInputStream(new ByteArrayInputStream(BO.toByteArray()));
        Cat cloneCat = (Cat) inputStream.readObject();
        System.out.println("原始的"+cat);
        System.out.println("克隆后"+cloneCat);
        //改变ear
        ear.setEarNum(6);
        ear.setEarName("右耳朵");
        System.out.println("改变ear后的原对象-----"+cat);
        System.out.println("改变ear后克隆对象-----"+cloneCat);


    }
}
