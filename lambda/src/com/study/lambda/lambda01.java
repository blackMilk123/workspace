package com.study.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-07-22 22:31
 **/
public class lambda01{

    public static void main(String[] args) {

        new Thread(() -> System.out.println("启动成功")).start();

    }
}
