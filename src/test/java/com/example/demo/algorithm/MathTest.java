package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * @author guozixuan
 * @date 2024/6/14 14:28
 */
public class MathTest {

    @Test
    public void test() {
//        System.out.println(accountBalanceAfterPurchase(18));
//        System.out.println(isHappy(19)); // 非快乐数即超时
//        System.out.println(isHappy1(19)); // 哈希
        System.out.println(isPowerOfFour(16));
    }

    public int accountBalanceAfterPurchase(int purchaseAmount) {
        if (purchaseAmount == 100) {
            return 0;
        }
        double s = (double) purchaseAmount / 10 % 10;
        // 四舍五入
        return (int) (100 - Math.round(s) * 10);
    }

    public boolean isHappy(int n) {
        String str = String.valueOf(n);
        int totalValue = getTotalValue(str);
        while (totalValue != 1) {
            totalValue = getTotalValue(String.valueOf(totalValue));
        }
        return true;
    }

    private int getTotalValue(String str) {
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            int num = Character.getNumericValue(str.charAt(i));
            total += num * num;
        }
        return total;
    }

    //思路：如果数字不快乐，那它一定重复了
    public boolean isHappy1(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = n;
        while (sum != 1) {
            sum = cal(sum);
            //先判断哈希表中是否存在这个数
            if (set.contains(sum)) {
                return false;
            }
            //如果不再将每次结果放到哈希表中去
            set.add(sum);
        }
        return true;
    }

    public int cal(int n) {
        int k = 0;
        while (n != 0) {
            k += (n % 10) * (n % 10);
            n /= 10;
        }
        return k;
    }

    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 4 == 0) {
            return isPowerOfFour(n / 4);
        } else {
            return false;
        }
    }

}
