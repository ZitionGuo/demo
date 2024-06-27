package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guozixuan
 * @date 2024/6/17 10:54
 */
public class DynamicPrograming {

    @Test
    public void test() {
        System.out.println(generate(6));
        System.out.println(minDistance("intention", "execution"));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> rowFirst = new ArrayList<>(1);
        rowFirst.add(1);
        result.add(rowFirst);
        if (numRows == 1) {
            return result;
        }
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            // 中间元素累加 第三行开始（即i=2）
            for (int j = 1; j < i; j++) {
                int count = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                list.add(count);
            }
            list.add(1);
            result.add(list);
        }
        return result;
    }

    /**
     * word1 转为 word2
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    public int minDistance(String word1, String word2) {
        int count = 0;
        if (word1.equals(word2)) return 0;
        count += Math.abs(word1.length() - word2.length());
        if (word1.length() > word2.length()) {

        }
        return count;
    }
}
