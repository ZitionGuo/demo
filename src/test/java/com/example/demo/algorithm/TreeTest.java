package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author guozixuan
 * @date 2024/6/14 14:16
 */
public class TreeTest {

    @Test
    public void test() {
//        TreeNode treeNode = new TreeNode(2);
//        TreeNode left = new TreeNode(3);
//        treeNode.left = left;
//        treeNode.right = new TreeNode(4);
//        treeNode.right.left = new TreeNode(9);
//        treeNode.right.right = new TreeNode(10);
//        left.left = new TreeNode(1);
//        left.right = new TreeNode(6);
//        left.left.left = new TreeNode(7);
//        left.left.right = new TreeNode(8);
//        System.out.println(treeNode);
//        System.out.println(inorderTraversal(treeNode));
//        System.out.println(inorderTraversal1(treeNode));
//        TreeNode a = new TreeNode(1);
//        a.left = new TreeNode(1);
//        a.right = new TreeNode(2);
//        TreeNode b = new TreeNode(1);
//        b.left = new TreeNode(1);
//        b.right = new TreeNode(2);
//        System.out.println(isSameTree(a, b));
//        TreeNode symmetricNode = new TreeNode(1);
//        symmetricNode.left = new TreeNode(2);
//        symmetricNode.left.left = new TreeNode(3);
//        symmetricNode.left.right = new TreeNode(4);
//        symmetricNode.right = new TreeNode(2);
//        symmetricNode.right.left = new TreeNode(4);
//        symmetricNode.right.right = new TreeNode(3);
//        System.out.println(symmetricNode);
//        System.out.println(isSymmetric(symmetricNode));
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

    // inorder: 左子树 -> 根节点 -> 右子树
    // 递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    // 栈
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // method 1: 遍历后校验两个list是否一致。。。
        // method 2: 递归左右遍历
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 对称二叉树
    // 递归
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    public int maxDepth(TreeNode root) {
        return 0;
    }

}
