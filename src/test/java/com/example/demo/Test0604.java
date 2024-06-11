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

    static Map<Character,Character> brackets = new HashMap();
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
//        System.out.println(isValid1("([)]"));
//        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
//        System.out.println(removeDuplicates1(new int[]{0,0,1,1,1,2,2,3,3,4}));
//        ListNode list1 = new ListNode(1);
//        list1.next = new ListNode(3);
//        ListNode list2 = new ListNode(2);
//        list2.next = new ListNode(4);
//        System.out.println(mergeTwoLists(list1, list2));
//        System.out.println(mergeTwoLists1(list1, list2));
        System.out.println(strStr("sadbutsad", "sad"));
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
        if(nums == null || nums.length == 0) return 0;
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                nums[slow + 1] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow + 1;
    }
    @Data
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

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
    //输入：haystack = "leetcode", needle = "leeto"
    //输出：-1
    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        }
        return haystack.indexOf(needle);
//        int i = 0;
//        while (i < needle.length()) {
//            if (haystack.charAt(i) != needle.charAt(i) ) {
//                return -1;
//            }
//            if (i == needle.length() - 1) {
//                return needle.length();
//            }
//        }
//        return -1;
    }


}
