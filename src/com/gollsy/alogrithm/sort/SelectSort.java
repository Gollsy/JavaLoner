package com.gollsy.alogrithm.sort;

import java.util.Arrays;

/**
 * @author Admin
 * @version 1.0
 * @className SelectSort
 * @description 选择排序-简单选择排序与【堆排序】
 * @date 2022/11/28 16:57
 */
public class SelectSort extends Sort {

    public static void main(String[] args) {
        int[] numbers = generateDisorderNumbers();
        System.out.println("原始数据：" + Arrays.toString(numbers));

        int[] numbers1 = Arrays.copyOf(numbers, numbers.length);
        selectSort(numbers1);
        System.out.println("选择排序：" + Arrays.toString(numbers1));

        int[] numbers2 = Arrays.copyOf(numbers, numbers.length);
        heapSort(numbers2);
        System.out.println("堆排序：" + Arrays.toString(numbers2));
    }

    /**
     * 【简单选择排序】
     * 【思想】：每次从[i+1,i+2,……,n]中选择一个最小的元素，与number[i+1]交换位置，以加入到有序表[0,1,……,i]中
     *         很像冒泡排序，但是一次冒泡会有许多元素交换位置，而简单选择排序每次只会有两个元素交换位置
     * 【空间复杂度】：O(1)
     * 【时间复杂度】：相比交换排序或是插排，选择排序的不用频繁的移动位置，最好的情况下，有序表的比较次数是0次，最坏也不超过 3n(n-1)，即O(n^2)
     *              但比较的次数并不会因此减少，而且【与初始序列无关】,始终是 n(n-1)/2 次，即O(n^2)
     *              因此时间复杂度始终是O(n^2)
     * 【稳定性】：同样会发生相同元素变换位置的情况，故而不稳定
     *
     * @param numbers
     */
    public static void selectSort(int[] numbers) {
        int i, j, min, tmp;
        int moveCount = 0, compareCount = 0;
        for (i = 0; i < numbers.length - 1; i++) {
            min = i;
            for (j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[min] && compareCount++ >= 0) min = j;
            }
            if (min != i) {
                tmp = numbers[i];
                numbers[i] = numbers[min];
                numbers[min] = tmp;
                moveCount += 3;
            }
        }
        System.out.println("\n选择排序：moveCount=" + moveCount + "  compareCount=" + compareCount);
    }

    private static int moveCount, compareCount;

    /**
     * 【堆排序】
     * 【思想】：通过建立大顶堆（就是将待排序列表建立为【完全二叉树】，然后自底向上调整），每次将堆顶元素（第一个元素）与堆底元素（最后一个元素）交换位置
     *         接着继续调整堆，重复上述步骤，直到遍历完全部元素
     * 【空间复杂度】：O(1)
     * 【时间复杂度】：建堆时间为O(n)，之后有n-1次向下调整的操作，每次是O(logn)，所以最好、最坏和平均情况下都是O(n*logn)
     * 【适用】：适合大量关键字的情况
     * 【稳定性】：相同的记录同样可能会交换次序，故而不稳定
     */
    public static void heapSort(int[] numbers) {
        buildMaxHeap(numbers);
        int tmp;
        for (int i = numbers.length-1; i > 1; i--) {
            tmp = numbers[i];
            numbers[i] = numbers[1];
            numbers[1] = tmp;
            moveCount += 3;
            headAdjust(numbers, 1, i - 1);
        }
        System.out.println("\n堆排序：moveCount=" + moveCount + "  compareCount=" + compareCount);
    }

    public static void buildMaxHeap(int[] numbers) {
        for (int i = numbers.length / 2; i > 0; i--) {
            headAdjust(numbers, i, numbers.length);
        }
    }

    private static void headAdjust(int[] numbers, int index, int length) {
        numbers[0] = numbers[index];
        moveCount++;
        for (int i = 2 * index; i <= length; i *= 2) {
            if (i < length-1 && numbers[i] < numbers[i + 1] && compareCount++ >= 0) {
                i++;
            }
            if (numbers[0] > numbers[i] && compareCount++ >= 0) break;
            else {
                numbers[index] = numbers[i];
                moveCount++;
                index = i;
            }
        }
        numbers[index] = numbers[0];
        moveCount++;
    }

}
