package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

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
//        System.out.println(majorityElementSecondVersion(new int[]{6,6,5,5,3,4,6,5}));
//        System.out.println(isIsomorphic("paper", "title")); // 很麻烦
//        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1}, 2)); //
//        System.out.println(containsNearbyDuplicate1(new int[]{1,2,3,1}, 3)); // bad performance
        System.out.println(lengthOfLongestSubstring("pwwkew"));

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

    // s = "paper", t = "title" -> true
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        LinkedHashMap<String, List<Integer>> mapS = getMap(s);
        LinkedHashMap<String, List<Integer>> mapT = getMap(t);
        System.out.println(mapS);
        System.out.println(mapT);
        if (mapS.size() != mapT.size()) {
            return false;
        }
        ArrayList<String> listS = new ArrayList<>(mapS.keySet());
        ArrayList<String> listT = new ArrayList<>(mapT.keySet());
        for (int i = 0; i < mapS.size(); i++) {
            List<Integer> list1 = mapS.get(listS.get(i));
            List<Integer> list2 = mapT.get(listT.get(i));
            if (list1.size() != list2.size()) {
                return false;
            }
            for (int j = 0; j < list1.size(); j++) {
                if (!list1.get(j).equals(list2.get(j))) {
                    return false;
                }
            }

        }
        // 记入元素位置 遍历匹配
        return true;
    }

    private LinkedHashMap<String, List<Integer>> getMap(String str) {
        LinkedHashMap<String, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            String key = String.valueOf(str.charAt(i));
            List<Integer> value = map.get(key);
            if (value == null || value.isEmpty()) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(key, list);
            } else {
                value.add(i);
                map.put(key, value);
            }
        }
        return map;
    }

    public boolean isIsomorphic1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (mapS.containsKey(charS) && mapS.get(charS) != charT) {
                return false;
            }
            if (mapT.containsKey(charT) && mapT.get(charT) != charS) {
                return false;
            }

            mapS.put(charS, charT);
            mapT.put(charT, charS);
        }

        return true;
    }

    // 1,0,1,1
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            Integer val = map.get(key);
            if (val != null && i - val <= k) {
                return true;
            }
            map.put(key, i);
        }
        return false;
    }

    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            List<Integer> val = map.get(key);
            if (val != null) {
                val.add(i);
                map.put(key, val);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(key, list);
            }
        }
        // 遍历 keySet，比较每组 list 元素间差值是否有 <= k 的
        for (Integer key : map.keySet()) {
            List<Integer> integers = map.get(key);
            if (integers.size() == 1) {
                continue;
            }
            for (int i = 0; i < integers.size() - 1; i++) {
                if (integers.get(i+1) != null) {
                    if (integers.get(i+1) - integers.get(i) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 解答失败:
     * 	测试用例:"pwwkew"
     * 	测试结果:4
     * 	期望结果:3
     * 	stdout:
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int longestLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char key = s.charAt(right);
            if (set.contains(key)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(key);
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }

}
