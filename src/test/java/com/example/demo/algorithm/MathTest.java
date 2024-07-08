package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Stack;

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
//        System.out.println(isPowerOfFour(16));
//        System.out.println(sumOfTheDigitsOfHarshadNumber(18));
        System.out.println(addStrings("1785", "9256")); // 栈版
        System.out.println(addStrings1("1785", "9256")); // GPT 优化版
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

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        String s = String.valueOf(x);
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            temp += s.charAt(i) - '0';
        }
        return x % temp == 0 ? temp : -1;
    }

    // 123 6791
    public String addStrings(String num1, String num2) {
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        for (int i = 0; i < num1.length(); i++) {
            stack1.add(num1.charAt(i) + "");
        }
        for (int i = 0; i < num2.length(); i++) {
            stack2.add(num2.charAt(i) + "");
        }
        StringBuilder sb = new StringBuilder();
        boolean carry = false;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int endNum = carry ? 1 : 0;
            if (!stack1.isEmpty()) {
                endNum += Integer.parseInt(stack1.pop());
            }
            if (!stack2.isEmpty()) {
                endNum += Integer.parseInt(stack2.pop());
            }
            if (endNum >= 10) {
                endNum = endNum % 10;
                carry = true;
            } else {
                carry = false;
            }
            sb.append(endNum);
        }
        if (carry) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    public static String addStrings1(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + carry;

            sb.append(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }

        return sb.reverse().toString();
    }

}
