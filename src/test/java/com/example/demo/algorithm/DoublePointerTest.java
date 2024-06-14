package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/14 14:19
 */
public class DoublePointerTest {

    @Test
    public void test() {
        System.out.println(removeDuplicates1(new int[]{0,0,1,1,1,2,2,3,3,4}));
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
}
