package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author guozixuan
 * @date 2024/6/14 14:13
 */
public class StackTest {


    @Test
    public void test() {
        System.out.println(isValid("([)]")); // wrong method
        System.out.println(isValid1("([)]")); // wrong method
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
}
