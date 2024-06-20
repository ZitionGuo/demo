package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @author guozixuan
 * @date 2024/6/14 14:16
 */
public class MapTest {

    @Test
    public void test() {
//        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
//        System.out.println(majorityElement(new int[]{3,2,3}));
//        System.out.println(majorityElement1(new int[]{3,4,5,5,4,4,5,5,5}));
        System.out.println(majorityElementSecondVersion(new int[]{6,6,5,5,3,4,6,5}));
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

    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     *
     * 示例 2：
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     */
    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        int middle = length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.get(num);
            if (count != null) {
                map.put(num, ++count);
                if (count > middle) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return 0;
    }

    // gpt 优化版
    public int majorityElement2(int[] nums) {
        int length = nums.length;
        int middle = length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > middle) {
                return num;
            }
            map.put(num, count);
        }
        return 0;
    }

    // 摩尔投票
    public int majorityElement1(int[] nums) {
        int count = 0;
        int candidate = 0;

        // First pass to find a potential candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            System.out.println("num:" + num);
            System.out.println("candidate:" + candidate);
            count += (num == candidate) ? 1 : -1;
            System.out.println(count);
        }
        return candidate;
    }

    public List<Integer> majorityElementSecondVersion2(int[] nums) {

        List<Integer> resultList = new ArrayList<>();
        return resultList;
    }

    public List<Integer> majorityElementSecondVersion(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int third = nums.length / 3;
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, ++count);
            }
        }
        System.out.println(map);
        List<Integer> resultList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            Integer count = map.get(key);
            if (count > third) {
                resultList.add(key);
            }
        }
        return resultList;
    }

}
