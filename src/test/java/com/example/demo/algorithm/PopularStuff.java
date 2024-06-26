package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/26 14:49
 */
public class PopularStuff {

    @Test
    public void test() {
        // 123456
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println(reverseList(listNode));
    }

    // 1 -> 2 -> 3 -> 4 -> 5
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next; // 暂时存储下一个节点
            // next temp:  2，3，4，5
            // next temp: 3,4,5
            // next temp: 4,5
            curr.next = prev; // *core
            // cur.next = null
            // cur.next = 1
            // cur.next = 2
            prev = curr; // *core
//            System.out.println(prev);
            // pre = 1 -> null
            // pre = 2 -> 1 -> null
            // pre - 3 -> 2 -> 1 -> null
            curr = nextTemp;
            System.out.println(curr);
            // curr = 2,3,4,5
            // curr = 3,4,5
            // curr = 4,5
        }
        return prev;
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
}
