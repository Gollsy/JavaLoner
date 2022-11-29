package com.gollsy.alogrithm.sort;

import java.util.Arrays;

/**
 * @author Admin
 * @version 1.0
 * @className MergeSort
 * @description 归并排序
 * @date 2022/11/29 15:12
 */
public class MergeSort extends Sort {

    public static int[] tmp;

    private static int moveCount, compareCount;

    public static void main(String[] args) {
        int[] numbers = generateDisorderNumbers();
        System.out.println("原始数据：" + Arrays.toString(numbers));
        tmp=new int[numbers.length];

        int[] numbers1 = Arrays.copyOf(numbers, numbers.length);
        mergeSort(numbers1);
        System.out.println("归并排序：" + Arrays.toString(numbers1));
    }

    /**
     * 【归并排序】
     * 【思想】：初始时，将每个元素看作一个单独的子序列，归并排序就是将K个相邻的子序列排序，然后重复该过程，直到递归结束，这叫做【K路归并排序】
     * 【空间复杂度】：有一个同大小的辅助数组，所以是O(n)
     * 【时间复杂度】：每一趟归并需要对n个元素都进行一次遍历，所以是每次归并是O(n)，共需要logn次归并，所以总共是O(n*logn)
     * 【稳定性】：稳定
     * @param numbers
     */
    public static void mergeSort(int[] numbers){
        mergeSort(numbers,1, numbers.length-1);
        System.out.println("\n归并排序：moveCount=" + moveCount + "  compareCount=" + compareCount);
    }

    public static void mergeSort(int[] numbers,int low,int high){
        if(low<high){
            int mid = (low+high)/2;
            mergeSort(numbers,low,mid);
            mergeSort(numbers,mid+1,high);
            merge(numbers,low,mid,high);
        }

    }

    public static void merge(int[] numbers, int low, int mid, int high) {
        if (high + 1 - low >= 0) System.arraycopy(numbers, low, tmp, low, high + 1 - low);
        int i, j, k;
        for (i = low, j = mid + 1, k = i; i <= mid && j <= high; k++) {
            if(tmp[i]<=tmp[j]&& compareCount++ >= 0){
                numbers[k]=tmp[i++];
                moveCount++;
            }else {
                numbers[k]=tmp[j++];
                moveCount++;
            }
        }
        while(i<=mid) {
            numbers[k++]=tmp[i++];
            moveCount++;
        }
        while(j<=high) {
            numbers[k++]=tmp[j++];
            moveCount++;
        }
    }
}
