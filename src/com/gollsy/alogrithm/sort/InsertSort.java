package com.gollsy.alogrithm.sort;

import java.util.Arrays;

/**
 * @author Admin
 * @version 1.0
 * @className InsertSort
 * @description 插入排序——直插排，折半插排，希尔插排
 * @date 2022/11/28 9:15
 */
public class InsertSort extends Sort {

    public static void main(String[] args) {
        int[] numbers = generateDisorderNumbers();
        System.out.println("原始数据：" + Arrays.toString(numbers));

        int[] numbers1 = Arrays.copyOf(numbers,numbers.length);
        directInsertSort(numbers1);
        System.out.println("直接插排：" + Arrays.toString(numbers1));

        int[] numbers2 = Arrays.copyOf(numbers,numbers.length);
        binaryInsertSort(numbers2);
        System.out.println("折半插排：" + Arrays.toString(numbers2));

        int[] numbers3 = Arrays.copyOf(numbers,numbers.length);;
        shellInsertSort(numbers3);
        System.out.println("希尔插排：" + Arrays.toString(numbers3));
    }

    /**
     * 【直接】插入排序
     * 【思想】：numbers[1……i]是有序数列，将numbers[i+1]插入到前面的有序数列中，从i-1开始【从后往前】比较，同时元素不断后移一位，直到插入到正确位置
     * 【空间复杂度】：很明显是O(1).
     * 【时间复杂度】：最坏的情况是将一个【递减数列】排序成默认的【递增数列】，最多的比较次数为2+3+4+……+n，【即O(n^2)】
     *              讨论移动次数，最坏的情况下，每个元素都需要先移到哨兵(0)位置，然后全部前移，故而移动次数为3+4+……+(n+1)，【即O(n^2)】
     * 【适用性】：【顺序存储】的【线性表】，【链式存储】的【线性表】（从前往后比较替换）
     * 【稳定性】：稳定
     *
     * @param numbers
     */
    public static void directInsertSort(int[] numbers) {
        int i, j;
        int moveCount =0, compareCount = 0;
        for (i = 2; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1] && compareCount++>=0) {
                numbers[0] = numbers[i];
                moveCount++;
                //已知numbers[i+1]<numbers[i]，接下来在前i-1个序列中寻找合适的位置
                //当跳出循环时，合适的位置j会自减一
                for (j = i - 1; numbers[0] < numbers[j] && compareCount++>=0; j--) {
                    numbers[j + 1] = numbers[j];
                    moveCount++;
                }
                //所以这边j+1
                numbers[j + 1] = numbers[0];
                moveCount++;
            }
        }
        System.out.println("\n直接插入排序：moveCount="+moveCount+"  compareCount="+compareCount);
    }

    /**
     *  【折半】插入排序
     *  【思想】：通过折半减少比较次数，但是移动次数还是不变
     *  【空间复杂度】：O(1)
     *  【时间复杂度】：比较的次数缩小为O(n*logn)，但移动的次数仍旧是O(n^2)，最终的时间复杂度仍旧是O(n^2)
     *  【适用性】：对于数据量不大的线性表，往往能表现出很好的性能
     *  【稳定性】：稳定
     * @param numbers
     */
    public static void binaryInsertSort(int[] numbers) {
        int i, j;
        int high, low, mid;
        int moveCount =0, compareCount = 0;
        for (i = 2; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1] && compareCount++>=0) {
                high=i-1;
                low=1;
                //哨兵暂存值
                numbers[0] = numbers[i];
                moveCount++;
                while(low<=high){
                    mid = (high+low)/2;
                    if(numbers[mid]<numbers[0] && compareCount++>=0){
                        low = mid+1;
                    }else {
                        high=mid-1;
                    }
                }
                //通过折半找到插入的位置，此时low>high
                //跳出循环中之前，mid=high=low，然后根据numbers[0]与numbers[mid]的大小决定
                //如果numbers[0]<numbers[mid]，那么high--,插入的位置就是原来high的位置
                //如果numbers[0]>high,那么插入的位置就是high+1
                // 综上，插入的位置是high+1
                for(j=i-1;j>=high+1;j--){
                    numbers[j+1] = numbers[j];
                    moveCount++;
                }
                numbers[high+1] = numbers[0];
                moveCount++;
            }
        }
        System.out.println("\n折半插入排序：moveCount="+moveCount+"  compareCount="+compareCount);
    }

    /**
     * 【希尔排序】——插入排序
     * 【思想】：将有序表分为若干形如[1,1+d,1+2d,……,1+kd],[2,2+d,2+2d,……,2+kd]……的子序列，对这些子序列分别进行【直插排】；
     *         重新选定一个更小的d，重复上述步骤，直到d=1,此时所有记录都在同一个序列当中，最后进行一次直插排
     * 【空间复杂度】：O(1)
     * 【时间复杂度】：最坏的情况下，会达到O(n^2)；当n在某个范围内时，约为O(n^1.3)
     * 【稳定性】：不稳定，相同的元素可能会被交换次序，这是由于两个相同的元素可能会被划分到两个不同的组内，从而导致先后顺序的变化
     * 【适用】：由于需要随机访问，所以【仅支持顺序存储的线性表】
     * @param numbers
     */
    public static void shellInsertSort(int[] numbers) {
        int dk,i,j;
        int moveCount =0, compareCount = 0;
        //规定dk的取法为依次折半
        for (dk = numbers.length/2; dk>=1; dk=dk/2) {
            //从dk+1开始找起
            for(i=dk+1;i< numbers.length;i++){
                //在形如[t,t+d,t+2d,……,t+kd]中进行直插排
                if(numbers[i]<numbers[i-dk] && compareCount++>=0){
                    numbers[0] = numbers[i];
                    moveCount++;
                    //元素后移，其实就是直插排的过程
                    for(j=i-dk;j>0&&numbers[0]<numbers[j] && compareCount++>=0;j-=dk){
                        numbers[j+dk] = numbers[j];
                        moveCount++;
                    }
                    numbers[j+dk] = numbers[0];
                    moveCount++;
                }
            }
        }
        System.out.println("\n希尔插入排序：moveCount="+moveCount+"  compareCount="+compareCount);
    }
}
