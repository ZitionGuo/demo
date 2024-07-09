package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/25 17:25
 */
public class BinarySearchTest {

    @Test
    public void test() {
//        System.out.println(firstBadVersion(5));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int m = left + (right-left)/2;
            if (isBadVersion(m)) right = m - 1;
            else left = m + 1;
        }
        return left;
    }

    private boolean isBadVersion(int version) {
        return version >= 4;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
