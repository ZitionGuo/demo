package com.example.demo.algorithm;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author guozixuan
 * @date 2024/6/14 14:18
 */
public class LinkedListTest {

    @Test
    public void test() {
//        ListNode list1 = new ListNode(1);
//        list1.next = new ListNode(3);
//        ListNode list2 = new ListNode(2);
//        list2.next = new ListNode(4);
//        System.out.println(mergeTwoLists(list1, list2));
//        System.out.println(mergeTwoLists1(list1, list2));
//        System.out.println(mergeTwoLists2(list1, list2));
//        TreeNode symmetricNode = new TreeNode(1);
//        symmetricNode.left = new TreeNode(2);
//        symmetricNode.left.left = new TreeNode(3);
//        symmetricNode.left.right = new TreeNode(4);
//        symmetricNode.right = new TreeNode(2);
//        symmetricNode.right.left = new TreeNode(4);
//        symmetricNode.right.right = new TreeNode(3);
//        System.out.println(isSymmetric1(symmetricNode));
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(6);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next.next = new ListNode(6);
//        System.out.println(removeElements(listNode, 6));
        System.out.println(removeElements1(listNode, 6));
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

    // 队列 先进先出
    public boolean isSymmetric1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
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

    public ListNode removeElements(ListNode head, int val) {
        System.out.println(head);
        // 由于链表的第一个节点可能就是要删除的节点，直接删除头节点会使操作变得复杂。引入哑节点后，无论头节点是否需要被删除，都可以使用相同的逻辑处理，避免了对头节点的特殊处理。
        ListNode dummy = new ListNode(val-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
            System.out.println(dummy);
        }
        return dummy.next;
    }

    // 递归版本
    // 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6  6
    public ListNode removeElements1(ListNode head, int val) {
        // 递归的终止条件：如果当前节点是null，返回null
        if (head == null) {
            return null;
        }

        // 递归处理链表的其余部分
        head.next = removeElements1(head.next, val);
        System.out.println(head);

        // 判断当前节点是否需要删除
        if (head.val == val) {
            return head.next; // 当前节点需要删除，返回下一个节点
        } else {
            return head; // 当前节点不需要删除，返回当前节点
        }
    }
}
