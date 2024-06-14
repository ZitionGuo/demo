package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author guozixuan
 * @date 2024/6/14 14:27
 */
public class ArrayTest {

    @Test
    public void test() {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println(lengthOfLastWord("the last american generation  "));
        System.out.println(Arrays.toString(plusOne(new int[]{9}))); // wrong answer, 没考虑到进位情况
        System.out.println(Arrays.toString(plusOne1(new int[]{9,8,9})));
    }

    private String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty()) {
                return "";
            }
        }
        return prefix;
    }

    /**
     * 示例 1：
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2,_,_]
     * 解释：你的函数函数应该返回 k = 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3,_,_,_]
     * 解释：你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4。
     * 注意这五个元素可以任意顺序返回。
     */
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return k;
    }

    public int lengthOfLastWord(String s) {
        char[] array = s.toCharArray();
        int result = 0;
        boolean endFlag = true;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == ' ' && endFlag) {
                continue;
            }
            if (array[i] != ' ') {
                result++;
                endFlag = false;
            } else {
                break;
            }
        }
        return result;
    }

    // GPT optimise: 不转化为数组，空间复杂度 O(n) -> O(1)
    public int lengthOfLastWord1(String s) {
        int length = 0;
        boolean endFlag = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (endFlag) {
                    continue;
                } else {
                    break;
                }
            } else {
                length++;
                endFlag = false;
            }
        }
        return length;
    }

    public int[] plusOne(int[] digits) {
        digits[digits.length- 1] = digits[digits.length- 1] + 1;
        return digits;
    }

    // 989
    public int[] plusOne1(int[] digits) {
//        int total = 0;
        // 1,2,3
//        for (int i = digits.length - 1; i >= 0; i--) {
        // 通过乘10的个数算到总的值
//            total = total + digits[i] * ;
//        }
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10; // **core 9+1 % 10 等于0，走下一次循环。如果不等于0，说明正常累加了，直接返回数组即可
            System.out.println("i:" + i + ", digits[i]:" + digits[i]);
            System.out.println(digits[i] % 10);
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 9999 的特殊情况
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
