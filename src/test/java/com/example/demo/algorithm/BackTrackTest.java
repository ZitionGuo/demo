package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guozixuan
 * @date 2024/7/25 16:38
 */
public class BackTrackTest {

    @Test
    public void test1() {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        backTracking(nums, 0, res, subset);
        return res;
    }

    public void backTracking(int[] nums, int startIndex, List<List<Integer>> res, List<Integer> subset) {
        // 回溯树中的所有节点都要加入到结果集合中
        res.add(new ArrayList<>(subset));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            backTracking(nums, i + 1, res, subset);
            System.out.println(res);
            subset.remove(subset.size() - 1);
        }
    }

}
