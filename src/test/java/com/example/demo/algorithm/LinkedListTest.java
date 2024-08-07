package com.example.demo.algorithm;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(6);
//        listNode.next.next.next = new ListNode(3);
//        listNode.next.next.next.next = new ListNode(4);
//        listNode.next.next.next.next.next = new ListNode(5);
//        listNode.next.next.next.next.next.next = new ListNode(6);
//        System.out.println(removeElements(listNode, 6));
//        System.out.println(removeElements1(listNode, 6));
//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
//        System.out.println(reverseList(listNode));
//        System.out.println(addTwoNumbers(listNode, listNode)); // wrong - 超过 int / long 最大长度后报错 NumberFormatException
//        System.out.println(addTwoNumbers1(listNode, listNode));
//        System.out.println(reverseBetween(listNode, 2,4));
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(5);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);
//        System.out.println(deleteDuplicates(listNode));
//        System.out.println(removeNthFromFirst(listNode, 2));
//        System.out.println(removeNthFromEnd(listNode, 2));
//        System.out.println(removeNthFromEnd1(listNode, 2));
        System.out.println(sortList(listNode));
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
        ListNode dummy = new ListNode(val - 1);
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

    // 反转
    // 1，2，3，4
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode prev = null; // core
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode curNext = cur.next;  // 暂存下一个节点
//            System.out.println(cur);
            if (curNext == null) {
                newHead = cur;  // 更新新头节点
            }
            cur.next = prev;  // 反转当前节点的指针
            prev = cur;  // 更新 prev 为当前节点
            cur = curNext;  // 移动到下一个节点
        }
        return newHead;
    }

    // 双指针法
    public ListNode reverseList1(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode slowNode = head;
        ListNode fastNode = head.next;
        slowNode.next = null;
        while (fastNode != null) {
            ListNode nextNextNode = fastNode.next;
            fastNode.next = slowNode;
            slowNode = fastNode;
            fastNode = nextNextNode;
        }
        return slowNode;
    }

    /**
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     * 示例 2：
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 找到 left 前的节点
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 开始反转的节点
        ListNode start = pre.next;
        // 要反转的第一个节点的下一个节点
        ListNode then = start.next;
        /**
         * pre: 12345
         * start: 2345 - 245
         * then: 345 - 3245
         */
        for (int i = 0; i < right - left; i++) {
            System.out.println("pre1" + pre);
            start.next = then.next;
            System.out.println("pre2" + pre);
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }


    /**
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * <p>
     * 示例 3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        long num1 = getTotal(l1);
        long num2 = getTotal(l2);
        String total = String.valueOf(num1 + num2);
        ListNode dummy = new ListNode();
        ListNode listNode = new ListNode(total.charAt(0) - '0');
        dummy.next = listNode;
        int i = 1;
        while (i < total.length()) {
            listNode.next = new ListNode(total.charAt(i) - '0');
            listNode = listNode.next;
            i++;
        }
        return reverseList(dummy.next);
    }

    private long getTotal(ListNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        while (node.next != null) {
            sb.append(node.next.val);
            node = node.next;
        }
        return Long.parseLong(String.valueOf(sb.reverse()));
    }

    /**
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * <p>
     * 示例 3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    // 1221 true
    // 依托 StringBuilder，采用双指针，Stack 类似，pop 弹出顶部元素比较
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        int left = 0, right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) == sb.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    // 1 -> 1 -> 2 -> 3
    // 2 -> 3
    // 转化数据结构排除重复元素再拼接新的 node
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null) {
            // 如果当前节点是重复的
            if (head.next != null && head.val == head.next.val) {
                // 找到所有重复的节点
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // 跳过所有重复的节点 core
                prev.next = head.next;
            } else {
                // 没有重复的节点，移动prev指针
                prev = prev.next;
            }
            head = head.next;
        }

        return dummy.next;
    }

    /**
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * 计算长度拿到需要移除的第 N 个
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = getLength(head);
        int firstN = length - n; // 顺位删除的
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy; // 0 -> 1 -> xxx
        int i = 0;
        while (head != null && i < firstN) {
            cur.next = head; // 0 -> 1; 1 -> 2
            head = head.next; // 2 -> 3; 3
            cur = cur.next; // 1; 2
            i++;
        }
        System.out.println(cur);
        if (cur.next != null) {
            cur.next = cur.next.next;
        }

        return dummy.next;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    // 双指针法
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 创建一个虚拟节点，指向头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 定义两个指针，初始都指向虚拟节点
        ListNode first = dummy;
        ListNode second = dummy;

        // 让第一个指针先前进n+1步
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 然后两个指针同时前进，直到第一个指针到达链表的末尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 此时第二个指针指向要删除节点的前一个节点，删除该节点
        second.next = second.next.next;

        // 返回虚拟节点的下一个节点，即新的头节点
        return dummy.next;
    }

    // 哈希
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (!set.add(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }
        }
        return null;
    }

    // 双指针法 https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/10774/tu-jie-xiang-jiao-lian-biao-by-user7208t/
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    // 排序链表
    // 添加到集合，排序，再创建新链表逐一加入元素 - bad way but works
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (Integer integer : list) {
            cur.next = new ListNode(integer);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 归并排序，减少额外空间
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) return head;

        // Find the middle of the list
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null; // Split the list into two halves

        // Recursively sort both halves
        left = sortList(left);
        right = sortList(right);

        // Merge the sorted halves
        return merge(left, right);
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) cur.next = left;
        if (right != null) cur.next = right;
        return dummy.next;
    }
}
