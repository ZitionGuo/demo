package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/27 15:53
 */
public class MemorySearchTest {

    @Test
    public void test() {
        System.out.println(climbStairs(4));
    }

    /**
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     *  1. 1 阶 + 1 阶 + 1 阶
     *  2. 1 阶 + 2 阶
     *  3. 2 阶 + 1 阶
     *
     */
    // 动态规划：斐波那契数列
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // 记忆化搜索
    public int climbStairs1(int n) {
        if (n <= 1) {
            return 1;
        }

        int prev1 = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
