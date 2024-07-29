package com.example.demo.algorithm.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class TreeTest {

    @Test
    public void test1() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        System.out.println(inorderTraversal(treeNode)); // 2 1 3
        System.out.println(preorderTraversal(treeNode)); // 1 2 3
        System.out.println(postorderTraversal(treeNode)); // 2 3 1
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(3);
        System.out.println(isSameTree(treeNode, treeNode1));
        treeNode.left.left = new TreeNode(4);
        System.out.println(maxDepth(treeNode));
        System.out.println(minDepth(treeNode));


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
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    // 中序：左根右
    private void inorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    // 前序：根左右
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    // 后序：左右根
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        preorder(root.left, result);
        preorder(root.right, result);
        result.add(root.val);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        } else {
            return false;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return add(root, 0);

    }

    private int add(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        int leftDepth = add(root.left, depth); // 3 1 7 -> 4;
        int rightDepth = add(root.right, depth); //
        return Math.max(leftDepth, rightDepth);
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth); // 1
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

}
