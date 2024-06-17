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
}
