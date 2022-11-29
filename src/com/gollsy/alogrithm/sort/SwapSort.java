package com.gollsy.alogrithm.sort;

import java.util.Arrays;

/**
 * @author Admin
 * @version 1.0
 * @className SwitchSort
 * @description 交换排序——冒泡，快排；
 *              【交换排序每次都会使得一个元素出现在最终位置上】
 * @date 2022/11/28 14:52
 */
public class SwapSort extends Sort {

    public static void main(String[] args) {
        int[] numbers = generateDisorderNumbers();
        System.out.println("原始数据：" + Arrays.toString(numbers));

        int[] numbers1 = Arrays.copyOf(numbers, numbers.length);
        bubbleSort(numbers1);
        System.out.println("冒泡排序：" + Arrays.toString(numbers1));

        int[] numbers2 = Arrays.copyOf(numbers, numbers.length);
        quickSort(numbers2);
        System.out.println("快速排序：" + Arrays.toString(numbers2));
    }

    /**
     * 【冒泡排序】
     * 【思想】：每次排序都使一个元素出现在最终位置上，即使其【全局有序】，有序子列中的元素或者聚集在头部，或者聚集在尾部。
     * 【空间复杂度】：O(1)
     * 【时间复杂度】：最好的情况下，是一个有序表，那么一次冒泡就可以结束，此时无需移动，比较的次数为O(n-1)
     *              最坏的情况下，是一个逆序表，那么需要比较 n(n-1)/2次，移动 3n(n-1)/2次，即O(n^2)
     * 【稳定性】：稳定
     * 【适用】：需要随机访问，所以是【顺序存储的线性表】
     *
     * @param numbers
     */
    public static void bubbleSort(int[] numbers) {
        int i, j, tmp;
        int moveCount = 0, compareCount = 0;
        boolean flag;
        for (i = 0; i < numbers.length - 1; i++) {
            flag = false;
            for (j = numbers.length - 1; j > i; j--) {
                if (numbers[j - 1] > numbers[j] && compareCount++ >= 0) {
                    tmp = numbers[j];
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = tmp;
                    flag = true;
                    moveCount += 3;
                }
            }
            //如果本次排序没有出现冒泡，说明已经基本有序了，可以直接退出了
            if (!flag) {
                break;
            }
        }
        System.out.println("\n冒泡排序：moveCount=" + moveCount + "  compareCount=" + compareCount);
    }


    private static int compareCount=0;
    private static int moveCount=0;
    /**
     * 【快速排序】
     * 【思想】：基于【分治法】：首先选取一个元素作为【基准】，通常是以表首元素为基准，将待排序表划分为大于和小于基准的两个部分；
     *         此时基准元素的最终位置就已经得到了，即前一部分元素表的长度+1；
     *         对两个部分的元素表分别重复上述操作，直到子序列中为空或只有一个元素；
     *         快排的主要内容就是【交替搜索】和【交换】
     * 【核心】：选好基准后，如何快速的划分出两个部分，是影响快排性能的关键；
     *         同时，如何选取一个【基准】元素，确保每次都能尽量划分出相对等量的两部分，也是优化算法的一个方向
     * 【空间复杂度】：由于需要工作栈保存递归信息，在最好的情况下，需要O(logn)，底为2；最坏的情况下，就需要n-1，即O(n)；
     *              平均情况下，选择O(logn)表示其空间消耗
     * 【时间复杂度】：明显可知时间消耗与待排序数列的【对称性】有关。最坏的情况就是一个【逆序表】，此时选择表首元素作为基准，会划分出0和n-1的子序列
     *              此时的时间复杂度为O(n^2);
     *              最好的情况中，每次划分都能得到两个相对匀称的子序列，此时的时间复杂度为O(n*logn)
     * 【稳定性】：与折半插排类似，相同的两个元素其先后位置可能会发生变化，故而不稳定。
     *           在快排中，这种情况多发生于【基准】后半段中小于基准的元素，在调换到基准前半段时，相对位置就会发生改变。
     * 【适用】：根据算法，既要从前往后查，又要从后往前查，还要能随机访问，就只有【顺序存储的线性表】了
     *
     * @param numbers
     */
    public static void quickSort(int[] numbers) {
        quickSort(numbers,1, numbers.length-1);
        System.out.println("\n快速排序：moveCount=" + moveCount + "  compareCount=" + compareCount);
    }

    public static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            //选定基准元素，对带排序表进行划分，使基准元素回到最终位置上
            int pivotPos = partition(numbers, low, high);
            //递归对两个子表进行快排
            quickSort(numbers, low, pivotPos - 1);
            quickSort(numbers, pivotPos + 1, high);
        }
    }

    /**
     * 【划分算法】——快排的核心
     *
     * @param numbers
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[low];
        while (low < high) {
            //假设当前时第i次循环
            //从后往前找一个比【基准】小的元素
            while (low < high && numbers[high]>=pivot && compareCount++>=0)
                high--;
            //初始时，将该元素放到基准的位置上，low(0)=low
            //之后，放在low(i-1)指向的位置，该位置上的元素在上一次循环中，复制到了high(i-1)上，故可以覆盖
            numbers[low] = numbers[high];
            moveCount++;

            //从前往后找一个比【基准】大的元素
            while (low < high && numbers[low]<=pivot && compareCount++>=0)
                low++;
            //high(i)指向的元素已经复制到了low(i-1)上，故可以将low(i)的元素复制到high(i)上
            numbers[high] = numbers[low];
            moveCount++;
        }
        //跳出循环时，low(n)=high(n)，这个位置上是个重复的元素，故而可以直接覆盖成pivot
        numbers[low] = pivot;
        moveCount++;
        return low;
    }

}
