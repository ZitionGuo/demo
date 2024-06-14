package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/14 14:28
 */
public class MathTest {

    @Test
    public void test() {
        System.out.println(accountBalanceAfterPurchase(18));

    }

    public int accountBalanceAfterPurchase(int purchaseAmount) {
        if (purchaseAmount == 100) {
            return 0;
        }
        double s = (double) purchaseAmount / 10 % 10;
        // 四舍五入
        return (int) (100 - Math.round(s) * 10);
    }
}
