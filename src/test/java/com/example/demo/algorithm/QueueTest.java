package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * @author guozixuan
 * @date 2024/6/26 17:57
 */
public class QueueTest {

    @Test
    public void test() {
        System.out.println(findKthLargest(new int[]{5,4,3,2,1,5}, 3));
    }

    public int findKthLargest(int[] nums, int k) {
        // 小顶堆 与普通队列不同，优先队列元素是按排序顺序检索的。
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            System.out.println(queue);
            if (queue.size() > k) queue.poll();
        }
        return queue.poll();
    }
}
