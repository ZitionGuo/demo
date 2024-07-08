package com.example.demo.algorithm;

public class FindOneNum {

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 6};
        int target = 3;
        int r = arr.length - 1;
        int l = 0;
        int index = getIndex(arr, 0, 0 + ((r - l) >> 1), arr.length - 1, target);
        int index2 = getIndex2(arr, target);
        System.out.println(index);
        System.out.println(index2);
    }

    public static int getIndex(int[] arr, int left, int mid, int right, int target) {
        if (left <= right) {
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
                mid = left + ((right - left) >> 1);
                return getIndex(arr, left, mid, right, target);
            } else {
                right = mid - 1;
                mid = left + ((right - left) >> 1);
                return getIndex(arr, left, mid, right, target);
            }
        }
        return -1;
    }

    public static int getIndex2(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] < target) {
                l = mid + 1;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
