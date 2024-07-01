package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guozixuan
 * @date 2024/6/17 10:54
 */
public class DynamicPrograming {

    @Test
    public void test() {
//        System.out.println(generate(6));
//        System.out.println(minDistance("intention", "execution"));
//        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // wrong answer -- 贪心是错的，要用动态规划
        System.out.println(coinChange1(new int[]{1, 2, 5}, 11));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> rowFirst = new ArrayList<>(1);
        rowFirst.add(1);
        result.add(rowFirst);
        if (numRows == 1) {
            return result;
        }
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            // 中间元素累加 第三行开始（即i=2）
            for (int j = 1; j < i; j++) {
                int count = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                list.add(count);
            }
            list.add(1);
            result.add(list);
        }
        return result;
    }

    /**
     * word1 转为 word2
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    public int minDistance(String word1, String word2) {
        int count = 0;
        if (word1.equals(word2)) return 0;
        count += Math.abs(word1.length() - word2.length());
        if (word1.length() > word2.length()) {

        }
        return count;
    }

    /**
     * 示例 1：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int count = 0;
        int temp = 0;
        int sum = amount;
        for (int i = coins.length - 1; i >= 0; i--) {
            int coin = coins[i];
            while (coin <= sum) {
                count++;
                temp += coins[i];
                sum -= coin;
                if (temp == amount) {
                    return count;
                }
            }
        }
        return -1;
    }

    public int coinChange1(int[] coins, int amount) {
        // 如果金额为0，直接返回0
        if (amount == 0) return 0;

        // 创建dp数组，dp[i]表示金额i所需的最少硬币数
        int[] dp = new int[amount + 1];

        // 初始化dp数组，最大值为amount + 1（比amount多1）
        Arrays.fill(dp, amount + 1);

        System.out.println(Arrays.toString(dp));

        // 金额为0时所需硬币数为0
        dp[0] = 0;

        // 遍历每一个金额，从1到amount
        for (int i = 1; i <= amount; i++) {
            // 遍历每一个硬币
            for (int coin : coins) {
                // 如果当前硬币不大于当前金额
                if (coin <= i) {
                    // 更新dp[i]，选择使用当前硬币或者不使用
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 如果dp[amount]仍然是初始值，表示无法凑成该金额，返回-1
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
