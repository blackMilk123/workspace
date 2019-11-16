package com.study.lambda;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-09-26 16:00
 **/
public class FinalDemo  {
    public void test() throws Exception{

        try {

        }catch (Exception e){
            throw new Exception();
        }
    }
    public static void main(String[] args) {

        final StringBuffer buffer = new StringBuffer("测试");
        buffer.append("123");
        System.out.println(buffer);
    }
}
