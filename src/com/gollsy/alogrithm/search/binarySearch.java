package com.gollsy.alogrithm.search;

/**
 * @author Admin
 * @version 1.0
 * @className binarySearch
 * @description 二分查找
 * @date 2022/11/25 15:27
 */
public class binarySearch {

    /**
     * 循环实现
     * 【复杂度】：不会超过判定树的高度，故而O(log|n|)，底为2
     * @param dataField
     * @param target
     * @return
     */
    public static int search(int[] dataField, int target) {
        int low = 0, high = dataField.length - 1, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (dataField[mid] == target) {
                return mid;
            } else if (dataField[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
