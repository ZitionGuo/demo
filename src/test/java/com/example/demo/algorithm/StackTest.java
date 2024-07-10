package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author guozixuan
 * @date 2024/6/14 14:13
 */
public class StackTest {


    @Test
    public void test() {
//        System.out.println(isValid("([)]")); // wrong method
//        System.out.println(isValid1("([)]"));
//        System.out.println(calculate("5-2*2"));
//        System.out.println(removeDuplicateLetters("bcabc")); // Wrong Answer 元素顺序会被改变，acdb -> abcd
//        System.out.println(removeDuplicateLetters1("cbacdcbc")); //
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{75, 71, 69, 76})));
    }

    static Map<Character, Character> brackets = new HashMap<>();

    static {
        brackets.put(')', '(');
        brackets.put(']', '[');
        brackets.put('}', '{');
    }

    public boolean isValid(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        System.out.println(map);
        return getResult(map, '[', ']') && getResult(map, '(', ')') && getResult(map, '{', '}');

    }

    private boolean getResult(Map<Character, Integer> map, Character left, Character right) {
        Integer i = map.get(left);
        Integer i1 = map.get(right);
        if (i == null && i1 == null) {
            return true;
        }
        if (i == null || i1 == null) {
            return false;
        }
        return i < i1;
    }

    // ([)]
    private boolean isValid1(String s) {
        if (s.isEmpty()) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character left = brackets.get(s.charAt(i));
            System.out.println(left);
            if (left == null) {
                stack.push(s.charAt(i));
            } else if (stack.isEmpty() || !left.equals(stack.pop())) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public int calculate(String s) {
        int n = s.length();
        Deque<Integer> deque = new ArrayDeque<>();
        char design = '+';
        int num = 0;
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            if (Character.isDigit(a)) {
                num = num * 10 + a - '0';
            }
            if (!Character.isDigit(a) && a != ' ' || i == n - 1) {
                switch (design) {
                    case '+':
                        deque.push(num);
                        break;
                    case '-':
                        deque.push(-num);
                        break;
                    case '*':
                        int x = deque.pop();
                        deque.push(num * x);
                        break;
                    default:
                        deque.push(deque.pop() / num);
                        break;
                }
                design = a;
                num = 0;
            }
        }
        int res = 0;
        while (!deque.isEmpty()) {
            res += deque.pop();
        }
        return res;
    }

    /**
     * 示例 1：
     * 输入：s = "bcabc"
     * 输出："abc"
     * <p>
     * 示例 2：
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     */
    public String removeDuplicateLetters(String s) {
        if (s.isEmpty()) return null;
        PriorityQueue<Character> queue = new PriorityQueue<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!queue.contains(c)) {
                queue.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 输入：s = "c b a c d c b c"
     * 输出："acdb"
     */
    public String removeDuplicateLetters1(String s) {
        int[] lastIndex = new int[26]; // 记录每个字母最后出现的位置
        boolean[] seen = new boolean[26]; // 记录字母是否在栈中出现
        Stack<Character> stack = new Stack<>(); // 用来存储结果字母的栈

        // 记录每个字母最后出现的位置
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        System.out.println(Arrays.toString(lastIndex));

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen[c - 'a']) continue; // 如果字母已经在栈中出现过，则跳过

            // 确保栈中的字母按字典序排列，并且当前栈顶字母在后面还会出现
            while (!stack.isEmpty() && c < stack.peek() && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false; // 出栈并标记字母未出现
            }

            stack.push(c); // 当前字母入栈
            seen[c - 'a'] = true; // 标记当前字母已出现
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }


    /**
     * 示例 1:
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * 示例 2:
     * <p>
     * 输入: temperatures = [30,40,50,60]
     * 输出: [1,1,1,0]
     */
    // 75,71,69,76 -> 3,2,1,0
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        return result;
    }
}
