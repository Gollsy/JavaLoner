package com.gollsy.alogrithm.sort;

import java.util.Random;

/**
 * @author Admin
 * @version 1.0
 * @className Sort
 * @description todo
 * @date 2022/11/28 9:17
 */
public class Sort {
    private static final int MAX_SIZE = 20;

    public static int[] generateDisorderNumbers() {
        Random ra = new Random(100);
        //第一个元素留作可能会用到的哨兵
        int[] numbers = new int[MAX_SIZE+1];
        int count=0;
        while(++count<=MAX_SIZE){
            numbers[count] = ra.nextInt(50);
        }
        return numbers;
    }

}
