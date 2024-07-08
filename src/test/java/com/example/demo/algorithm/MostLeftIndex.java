package com.example.demo.algorithm;

public class MostLeftIndex {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4};
        int left = getMostLeftIndex(arr, 1);
        System.out.println(left);
    }

    private static int getMostLeftIndex(int[] arr, int target) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= target) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
}
