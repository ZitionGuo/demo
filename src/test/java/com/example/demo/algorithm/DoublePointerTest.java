package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author guozixuan
 * @date 2024/6/14 14:19
 */
public class DoublePointerTest {

    @Test
    public void test() {
//        System.out.println(removeDuplicates1(new int[]{0,0,1,1,1,2,2,3,3,4}));
//        ListNode list1 = new ListNode(3);
//        ListNode list2 = new ListNode(1);
//        list1.next = list2;
//        ListNode list3 = new ListNode(2);
//        list2.next = list3;
//        ListNode list4 = new ListNode(5);
//        list3.next = list4;
//        ListNode list5 = new ListNode(6);
//        list4.next = list5;
//        list5.next = list2;
//        System.out.println(hasCycle(list1));
//        moveZeroes(new int[]{0,1,0,3,12});
//        System.out.println(longestPalindrome("abcdbdc"));
//        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
//        reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
        System.out.println(reverseVowels(".,"));
    }

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

    //输入：head = [3,2,0,-4], pos = 1
    //输出：true
    //解释：链表中有一个环，其尾部连接到第二个节点。
    //
    //输入：head = [1,2], pos = 0
    //输出：true
    //解释：链表中有一个环，其尾部连接到第一个节点。
    // * 双指针
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        // core: 有环则快指针可以一直循环下去，fast.next 不会为 null
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 慢指针每次移动一步
            fast = fast.next.next;   // 快指针每次移动两步
            System.out.println("slow:" + slow.val + ", fast:" + fast.val);
            if (slow == fast) {      // 快慢指针相遇，说明存在环
                return true;
            }
        }
        return false; // 快指针到达链表末尾，说明不存在环
    }

    /**
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     */
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
//        int left = 0, right = length;
//        while (left < right) {
//            if (nums[left] == 0) {
//                nums[left] = nums[right];
//                nums[right--] = 0;
//            } else {
//                left++;
//            }
//        }
//        System.out.println(Arrays.toString(nums)); // [12, 1, 3, 0, 0] 前移会有问题
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[slow] = nums[i]; // 慢指针一直按照非 0 值的顺序存储
                slow++;
            }
        }
        for (int j = slow; j < nums.length; j++) {
            nums[j] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    public String longestPalindrome(String s) {
        return s;
    }

    // 1,8,6,2,5,4,8,3,7 -> 7*7 = 49
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return maxArea;
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        System.out.println(Arrays.toString(s));
    }

    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        String vowels = "aeiouAEIOU";
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            if (!vowels.contains(s.charAt(left) + "")) {
                ++left;
                continue;
            }
            if (!vowels.contains(s.charAt(right) + "")) {
                --right;
                continue;
            }
            char temp = s.charAt(left);
            sb.setCharAt(left, s.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
        return sb.toString();
    }
}
