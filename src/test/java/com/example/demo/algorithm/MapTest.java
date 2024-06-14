package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author guozixuan
 * @date 2024/6/14 14:16
 */
public class MapTest {

    @Test
    public void test() {
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
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

}
