package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
//        System.out.println(reverseVowels(".,"));
//        System.out.println(longestPalindrome("babad"));
//        System.out.println(detectCycle1(list1));
//        System.out.println(detectCycle(list1));
//        System.out.println(reverseWords("the sky is blue"));
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSum1(new int[]{-1,0,1,2,-1,-4}));
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
                    "val=" + val + '}';
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

    /**
     * answered by gpt
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * <p>
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 奇数长度回文
            int len2 = expandAroundCenter(s, i, i + 1); // 偶数长度回文
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        System.out.println(left);
        System.out.println(right);
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
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

    // GPT 优化版
    public String reverseVowels1(String s) {
        int left = 0;
        int right = s.length() - 1;
        String vowels = "aeiouAEIOU";
        StringBuilder sb = new StringBuilder(s);

        while (left < right) {
            // 使用 while 循环来找到左边第一个元音
            while (left < right && vowels.indexOf(s.charAt(left)) == -1) {
                left++;
            }
            // 使用 while 循环来找到右边第一个元音
            while (left < right && vowels.indexOf(s.charAt(right)) == -1) {
                right--;
            }

            // 交换元音
            if (left < right) {
                char temp = s.charAt(left);
                sb.setCharAt(left, s.charAt(right));
                sb.setCharAt(right, temp);
                left++;
                right--;
            }
        }

        return sb.toString();
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 慢指针每次移动一步
            fast = fast.next.next;   // 快指针每次移动两步
            // 相遇说明有环
            if (slow == fast) {
                ListNode index1 = fast;
                ListNode index2 = head;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1; //找到环入口，返回
            }
        }
        return null;
    }

    // 哈希
    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (!set.contains(node)) {
                set.add(node);
                node = node.next;
            } else {
                return node;
            }
        }
        return null;
    }

    public String reverseWords(String s) {
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            String item = array[i];
            if (item.isEmpty()) {
                continue;
            }
            sb.append(item);
            sb.append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     */
    // 全排列？+ containsAll？
    // TODO 全排列2 才能完成，因为这里包含重复元素，输出的全排列集合列表有问题
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> permute = permute(nums);
        System.out.println(permute);
        List<List<Integer>> removeList = new ArrayList<>();
        for (List<Integer> item : permute) {
            int total = 0;
            for (Integer num : item) {
                total += num;
            }
            if (total != 0) {
                removeList.add(item);
            }
        }
        permute.removeAll(removeList);
        return permute;
    }

    private List<List<Integer>> permute(int[] nums) {
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

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // 先对数组进行排序

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }


}
