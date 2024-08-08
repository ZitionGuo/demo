package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author guozixuan
 * @date 2024/6/25 17:25
 */
public class BinarySearchTest {

    @Test
    public void test() {
//        System.out.println(firstBadVersion(5));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(Arrays.toString(searchRange(new int[]{4, 5, 6, 7, 0, 1, 2}, 5)));
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int m = left + (right - left) / 2;
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

    public int[] searchRange(int[] nums, int target) {
        // 首个target如果存在，一定是首个大于target-1的元素
        int start = search(nums, target - 1);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};    // 首个target不存在，即数组中不包含target
        }
        // 找到首个大于target的元素，最后一个target一定是其前一位
        int end = search(nums, target);
        return new int[]{start, end - 1};
    }

    /**
     * 返回首个大于target的元素索引，如果不存在，返回数组长度n
     */
    private int search(int[] nums, int target) {
        // 二分查找区间[left, right)，初始为整个区间
        int left = 0;
        int right = nums.length;
        // 找到首个大于target的值
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid;    // 找到一个大于target的值，暂存并在左半区间继续查找
            } else {
                left = mid + 1; // 没有找到大于target的值，在右半区间继续查找
            }
        }
        return right;
    }

}
