package com.example.demo.algorithm.common;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author guozixuan
 * @date 2024/7/2 14:36
 */
public class RecursionTest {

    @Test
    public void test() {
//        System.out.println(fib(4));
//        System.out.println(fib1(4));
        System.out.println(decodeString("3[a2[c]]"));
    }

    // 斐波那契 0112358
    public int fib(int n) {
        if (n < 2) return n;
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

    /**
     * 示例 1：
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * <p>
     * 示例 2：
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * <p>
     */
    // 错误版本
    public String decodeStringWrong(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            if (pop == null) break;
            if (pop == ']' || pop == '[') continue;
            if (!Character.isDigit(pop)) {
                sb.append(pop);
            } else {
                for (int i = 0; i < pop - '0' - 1; i++) {
                    sb.append(sb);
                }
            }
        }
        return sb.reverse().toString();
    }

    // 3[a]2[bc]
    // 3[a2[c]]
    // 双栈解法
    public String decodeString(String s) {
        System.out.println(s);
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0'); // 转为 10 进制
            } else if (ch == '[') {
                countStack.push(k);
                stringStack.push(currentString.toString());
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = new StringBuilder(stringStack.pop());
                int currentK = countStack.pop();
                for (int i = 0; i < currentK; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }

    // 单栈解法
    public String decodeString2(String s) {
        Stack<Object> stack = new Stack<>();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                stack.push(k);
                k = 0;
            } else if (ch == ']') {
                StringBuilder encodedString = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() instanceof String) {
                    encodedString.insert(0, stack.pop());
                }
                int count = (int) stack.pop();
                StringBuilder decodedString = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    decodedString.append(encodedString);
                }
                stack.push(decodedString.toString());
            } else {
                stack.push(String.valueOf(ch));
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        return result.toString();
    }

    // 递归解法
    public String decodeString3(String s) {
        return decodeHelper(s, new int[]{0});
    }

    private String decodeHelper(String s, int[] index) {
        StringBuilder result = new StringBuilder();
        int k = 0;

        while (index[0] < s.length()) {
            char ch = s.charAt(index[0]);

            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
                index[0]++;
            } else if (ch == '[') {
                index[0]++;  // Skip '['
                String decodedString = decodeHelper(s, index);
                for (int i = 0; i < k; i++) {
                    result.append(decodedString);
                }
                k = 0;
            } else if (ch == ']') {
                index[0]++;  // Skip ']'
                return result.toString();
            } else {
                result.append(ch);
                index[0]++;
            }
        }

        return result.toString();
    }


}
