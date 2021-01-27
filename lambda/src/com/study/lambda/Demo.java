package com.study.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2021-01-04 20:39
 **/
public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        build(list);

        System.out.println(list.toString());
    }

    private static void build(List<String> list) {
        list.add("4444");
    }
}
