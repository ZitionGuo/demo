package com.example.demo.algorithm.common;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/7/2 14:36
 */
public class RecursionTest {

    @Test
    public void test() {
//        System.out.println(fib(4));
        System.out.println(fib1(4));
    }

    // 斐波那契 0112358
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 动态规划
    public int fib1(int n) {
        if (n <= 1) return n;
        // dp 数组里要放 n 个数，所以大小要为 n + 1，因为 n + 1 这个索引取不到
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


}
