package com.example.demo.algorithm;

public class T {
    public static void main(String[] args) {
        int[] coins = {1};
        int amount = 2;
        System.out.println(getMinCoins_general(coins, amount));
        System.out.println(getMinCoins_memorySearch(coins, amount));
    }


    /**
     * 记忆化搜索版本 -时间复杂度O(coins.length*amount)
     */
    static Integer min2 = Integer.MAX_VALUE;

    private static int getMinCoins_memorySearch(int[] coins, int amount) {
        //把一些不可能的过滤掉
        if (amount == 0) {//需要凑的amount==0 不需要凑 直接返回0个
            return 0;
        }
        if (coins == null || coins.length == 0) {//没有coins让你选 直接return -1
            return -1;
        }
        if (coins.length == 1 && amount % coins[0] != 0) {
            return -1;
        }
        //定义缓存表 初始化-2 因为-1被题目赋予特殊意义的值（找不到任何方法）
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -2;
            }
        }
        return search_process(coins, 0, amount, 0, dp);
    }

    private static int search_process(int[] coins, int index, int rest, int totalZhang, int[][] dp) {

        if (rest < 0) {//凑过头了 无效返回. rest<0 dp坐标是不可能为负数的因此不能存在dp中 由调用者去寄存
            return -1;
        }
        if (index == coins.length) {//已经走完整个coins,如果reset!=0则此方案不通retun,如果rest==0表明这种方案搞，则统计当前方案的总张数
            if (rest == 0) {
                min2 = Math.min(totalZhang, min2);
                dp[index][rest] = min2;
                return dp[index][rest];
            } else {
                dp[index][rest] = -1;
                return dp[index][rest];
            }
        }
        //dp的作用来了，如果dp[index][rest]中有值 可以返回就不用往下重复递归了
        if (dp[index][rest] != -2) {
            return dp[index][rest];
        }


        for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
            int r = search_process(coins, index + 1, rest - coins[index] * zhang, totalZhang + zhang, dp);
            if (r != -2) {
                dp[index][rest] = r;
            }

        }
        return dp[index][rest];
    }


    /**
     * 递归方法-事件复杂度（O^coins.length)
     */
    static Integer min = Integer.MAX_VALUE;

    private static int getMinCoins_general(int[] coins, int amount) {
        //把一些不可能的过滤掉
        if (amount == 0) {//需要凑的amount==0 不需要凑 直接返回0个
            return 0;
        }
        if (coins == null || coins.length == 0) {//没有coins让你选 直接return -1
            return -1;
        }
        if (coins.length == 1 && amount % coins[0] != 0) {
            return -1;
        }

        process(coins, 0, amount, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 从左往右去选择，conins[index]任意张 直到达到rest，统计凑成功的货币数
     *
     * @param coins 货币数组
     * @param index 正在尝试的coins[]的下标
     * @param rest  开始时rest==amount, 当使用coins[index]货币n张时候，rest-conins[index]*n,当走完整个coins[],如果reset==0表明凑成功了
     * @return
     */
    private static void process(int[] coins, int index, int rest, int totalZhang) {
        if (index == coins.length) {//已经走完整个coins,如果reset!=0则此方案不通retun,如果rest==0表明这种方案搞，则统计当前方案的总张数
            if (rest == 0) {
                min = Math.min(totalZhang, min);
            }
            return;
        }
        if (rest < 0) {//凑过头了 无效返回
            return;
        }

        for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
            process(coins, index + 1, rest - coins[index] * zhang, totalZhang + zhang);
        }

    }

}
