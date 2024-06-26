package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/25 17:25
 */
public class BinarySearchTest {

    @Test
    public void test() {
        System.out.println(firstBadVersion(5));
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
}
