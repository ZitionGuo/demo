package com.example.demo.algorithm;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author guozixuan
 * @date 2024/6/14 14:18
 */
public class LinkedListTest {

    @Test
    public void test() {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        System.out.println(mergeTwoLists(list1, list2));
        System.out.println(mergeTwoLists1(list1, list2));
        System.out.println(mergeTwoLists2(list1, list2));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        fillArrayList(list, list1);
        fillArrayList(list, list2);
        Collections.sort(list);
        System.out.println(list); // 1,2,3,4
        ListNode result = new ListNode(list.get(0));
        ListNode current = result; // *** core ***
        for (int i = 1; i < list.size(); i++) {
            current.next = new ListNode(list.get(i));
            current = current.next;
        }
        System.out.println(current);
        return result;
    }

    private void fillArrayList(ArrayList<Integer> arrayList, ListNode list) {
        while (list != null) {
            arrayList.add(list.val);
            list = list.next;
        }
    }

    // from gpt optimised
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // 创建一个哑节点
        ListNode current = dummy; // 当前节点从哑节点开始

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        /**
         * 假设以下两个链表：
         *
         * list1: 1 -> 3 -> 5
         * list2: 2 -> 4 -> 6 -> 8
         * 合并的过程如下：
         *
         * 比较1和2，取1，current指向1。
         * 比较3和2，取2，current指向2。
         * 比较3和4，取3，current指向3。
         * 比较5和4，取4，current指向4。
         * 比较5和6，取5，current指向5。
         * 此时，list1已经遍历完，但list2还有6和8未处理，所以将list2的剩余部分连接到新链表的末尾：
         * current.next = list2; // 此时 list2 为 6 -> 8
         */
        // 连接剩余的节点 ** core
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next; // 返回哑节点的下一个节点，即合并后的链表的头节点
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l2.next, l1);
            return l2;
        }
    }

    @Data
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
