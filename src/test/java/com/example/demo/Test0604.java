package com.example.demo;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author guozixuan
 * @date 2024/6/4 10:45
 */
public class Test0604 {

    static Map<Character, Character> brackets = new HashMap();

    static {
        brackets.put(')', '(');
        brackets.put(']', '[');
        brackets.put('}', '{');
    }

    @Test
    public void testOther() {
        String s = "index";
//        System.out.println(s.indexOf("index")); // 0
//        System.out.println(s.substring(0, 4)); // inde
//        System.out.println(isValid("([)]")); // wrong method
//        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
//        ListNode list1 = new ListNode(1);
//        list1.next = new ListNode(3);
//        ListNode list2 = new ListNode(2);
//        list2.next = new ListNode(4);
//        System.out.println(mergeTwoLists(list1, list2));
//        System.out.println(strStr("sadbutsad", "sad"));
//        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
//        System.out.println(accountBalanceAfterPurchase(18));
//        System.out.println(lengthOfLastWord("the last american generation  "));
//        System.out.println(Arrays.toString(plusOne(new int[]{9}))); // wrong answer, 没考虑到进位情况
//        System.out.println(Arrays.toString(plusOne1(new int[]{9,8,9})));
        System.out.println(addBinary("101", "111"));
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

    // 输入：nums = [0,0,1,1,1,2,2,3,3,4] 输出：5, nums = [0,1,2,3,4]
    public int removeDuplicates(int[] nums) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if (value == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], ++value);
            }
        }
        System.out.println(map);
        Set<Integer> integers = map.keySet();
        ArrayList<Integer> list = new ArrayList<>(integers);
        for (int i = 0; i < nums.length; i++) {
            if (i < list.size() && list.get(i) != null) {
                nums[i] = list.get(i);
            } else {
                nums[i] = 0;
            }
        }
        System.out.println(Arrays.toString(nums));
        return integers.size();
    }

    // [0,0,1,1,1,2,2,3,3,4] 输出：5, nums = [0,1,2,3,4]
    public int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                nums[slow + 1] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow + 1;
    }

    @Data
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        fillArrayList(list, list1);
        fillArrayList(list, list2);
        Collections.sort(list);
        System.out.println(list); // 1,2,3,4
        ListNode result = new ListNode(list.get(0));
        ListNode current = result; // *** core ***
        for (int i = 1; i < list.size(); i++) {
            current.next = new ListNode(list.get(i));
            current = current.next;
        }
        System.out.println(current);
        return result;
    }

    private void fillArrayList(ArrayList<Integer> arrayList, ListNode list) {
        while (list != null) {
            arrayList.add(list.val);
            list = list.next;
        }
    }

    // from gpt optimised
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // 创建一个哑节点
        ListNode current = dummy; // 当前节点从哑节点开始

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        /**
         * 假设以下两个链表：
         *
         * list1: 1 -> 3 -> 5
         * list2: 2 -> 4 -> 6 -> 8
         * 合并的过程如下：
         *
         * 比较1和2，取1，current指向1。
         * 比较3和2，取2，current指向2。
         * 比较3和4，取3，current指向3。
         * 比较5和4，取4，current指向4。
         * 比较5和6，取5，current指向5。
         * 此时，list1已经遍历完，但list2还有6和8未处理，所以将list2的剩余部分连接到新链表的末尾：
         * current.next = list2; // 此时 list2 为 6 -> 8
         */
        // 连接剩余的节点 ** core
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next; // 返回哑节点的下一个节点，即合并后的链表的头节点
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    // 示例 1：
    //输入：haystack = "sadbutsad", needle = "sad"
    //输出：0
    //解释："sad" 在下标 0 和 6 处匹配。
    //第一个匹配项的下标是 0 ，所以返回 0 。

    // 示例 2：
    //输入：haystack = "leetcode", needle = "tleeto"
    //输出：-1
    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        }
        return haystack.indexOf(needle);
    }

    public int strStr1(String haystack, String needle) {
        char[] array = haystack.toCharArray();
        int index;
        for (int i = 0; i < array.length; i++) {
            int j = 0;
            while (array[i] != needle.charAt(j)) {
                j++;
            }
        }
        return -1;
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

    // double pointer

    /**
     * 如果要移除的元素恰好在数组的开头，例如序列 [1,2,3,4,5]，当 val 为 1 时，我们需要把每一个元素都左移一位。
     * 注意到题目中说：「元素的顺序可以改变」。实际上我们可以直接将最后一个元素 5 移动到序列开头，取代元素 1，得到序列 [5,2,3,4]，同样满足题目要求。
     * 这个优化在序列中 val 元素的数量较少时非常有效。
     */
    public int removeElement1(int[] nums, int val) {
        int before = 0;
        int after = nums.length;
        while (before < after) {
            if (nums[before] == val) {
                nums[before] = nums[after - 1];
                after--;
            } else {
                before++;
            }
        }
        return before;
    }


    public int accountBalanceAfterPurchase(int purchaseAmount) {
        if (purchaseAmount == 100) {
            return 0;
        }
        double s = (double) purchaseAmount / 10 % 10;
        // 四舍五入
        return (int) (100 - Math.round(s) * 10);
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

    // 示例 1：
    //输入:a = "11", b = "1"
    //输出："100"
    //
    // 示例 2：
    //输入：a = "1010", b = "1011"
    //输出："10101"
    //
    // 提示：
    // 1 <= a.length, b.length <= 10⁴
    // a 和 b 仅由字符 '0' 或 '1' 组成
    // 字符串如果不是 "0" ，就不含前导零
    //

    public String addBinary(String a, String b) {
        int lengthA = a.length() - 1;
        int lengthB = b.length() - 1;
        StringBuilder result = new StringBuilder();
        int carry = 0;
        while (lengthA >= 0 || lengthB >= 0) {
            int sum = carry;
            if (lengthA >= 0) {
                sum += a.charAt(lengthA) - '0'; // 字符转化为整数
                lengthA--;
            }
            if (lengthB >= 0) {
                sum += b.charAt(lengthB) - '0';
                lengthB--;
            }
            result.append(sum % 2); // sum can be 0, 1, 2, or 3
            carry = sum / 2; // 这一行代码的作用是计算进位值。
            System.out.println("result:" + result + ", sum:" + sum + ", carry:" + carry);
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
