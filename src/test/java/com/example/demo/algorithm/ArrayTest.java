package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guozixuan
 * @date 2024/6/14 14:27
 */
public class ArrayTest {

    @Test
    public void test() {
//        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
//        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
//        System.out.println(lengthOfLastWord("the last american generation  "));
//        System.out.println(Arrays.toString(plusOne(new int[]{9}))); // wrong answer, 没考虑到进位情况
//        System.out.println(Arrays.toString(plusOne1(new int[]{9,8,9})));
//        System.out.println(maxProfit(new int[]{7,1,5,3,6,4})); // 长度很大超时。。。
//        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(permute(new int[]{1,2,3,4}));

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

    // 最大利息
    // 输入：[7,1,5,3,6,4]，输出：5（6-1）
    // 选择一个作为买入值，拿后面的减去该值，获取最大利息
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                System.out.println("i: " + i + ", j: " + j);
                if (prices[i] > prices[j]) {
                    continue;
                }
                System.out.println("price: [j] = " + prices[j]);
                result = Math.max(result, prices[j] - prices[i]);
                System.out.println("result " + result);
            }
        }
        return result;
    }

    /**
     * 解释2,6,1,4这样的情况
     * 简单来说:是如下的情况(实际会比较的多一点,但是不影响总体)
     * 首先,minPrice = 2 / profit = 6-2 = 4
     * 然后找到一个更小的minPrice = 1  之后计算出来的profit = 4-1 = 3
     *
     * 最后比较两个profit,谁更大就取谁!  这才是关键,谁更大就取谁!
     *
     * 等于将股价分为多段--->
     * 条件:要求每段都是升序,并且后面的价格段中minPrice比前面的minPrice更小,后面的价格段以minPrice开头
     *
     * 例如3,100,2,99,1,90 --> 这样算法就将这段数字分为三段,[3,100] [2,99] [1,90],
     * 然后分别计算他们的profit,最后比较它们的大小,取最大的profit,返回
     *
     * 换个例子,如果是这样的情况:[7,2,10,3,1,40]
     * 那么就分为[7,2,10,3] [1,40]
     * 第一段是最佳profit是8, 第二段的最佳profit是39,最后比较它们,取更大值39
     *
     * 这即为动态规划的思路,求解多段问题,记录前面的最优解,然后与现在的最优解进行比较交换,最后剩下的就是最优解答案
     */
    public int maxProfit1(int[] prices) {
        // 如果数组为空或长度为0，直接返回0
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE; // 初始化一个极大值，表示股票的最低价格
        int maxProfit = 0; // 初始化最大利润为0

        // 遍历所有股票价格
        for (int price : prices) {
            // 如果当前价格比minPrice小，更新minPrice
            if (price < minPrice) {
                minPrice = price; // 7 2
            }
            // 否则，计算当前价格卖出时的利润，并更新maxProfit
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice; // 10-1
            }
        }
        return maxProfit; // 返回最大利润
    }

    /**
     * 示例 1：
     *
     * 输入: s = "A man, a plan, a canal: Panama"
     * 输出：true
     * 解释："amanaplanacanalpanama" 是回文串。
     * 示例 2：
     *
     */
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.trim().isEmpty()) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        System.out.println(sb);
        int left = 0;
        int right = sb.length()-1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 全排列 dizzy
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] 2*3
     * 1234 1243 1324 1342 1423 1432 6*4
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    continue; // 已经包含的数字不再添加
                }
                tempList.add(num);
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void testTwoDimension() {
        int[][] num = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};    //创建二维数组并赋值
        for (int i = 0; i < num.length; i++) {             //for循环遍历输出数组
            for (int j = 0; j < num[i].length; j++) {
                System.out.print(num[i][j] + " ");
            }
        }
    }
}
