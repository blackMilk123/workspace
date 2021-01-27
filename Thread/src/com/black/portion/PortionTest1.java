package com.black.portion;

public class PortionTest1 {

    private static int  row = 1024;
    private static int  column = 1024;

    public static void main(String[] args) {
        long [][]  array = new long[row][column];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                array[i][j] = i*3+j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println(cacheTime);

    }

}
