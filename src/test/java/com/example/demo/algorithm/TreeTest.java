package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

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
//        a.left.left = new TreeNode(3);
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
//        TreeNode depthTree = new TreeNode(2);
//        depthTree.left = new TreeNode(4);
//        depthTree.right = new TreeNode(6);
//        depthTree.left.left = new TreeNode(7);
//        depthTree.left.right = new TreeNode(9);
//        depthTree.left.left.left = new TreeNode(11);
//        System.out.println(maxDepth(depthTree));
//        TreeNode preTreeNode = new TreeNode(2);
//        preTreeNode.left = new TreeNode(3);
//        preTreeNode.left.left = new TreeNode(1);
//        preTreeNode.left.right = new TreeNode(6);
//        preTreeNode.left.left.left = new TreeNode(7);
//        preTreeNode.left.left.right = new TreeNode(8);
//        preTreeNode.right = new TreeNode(4);
//        preTreeNode.right.left = new TreeNode(9);
//        preTreeNode.right.right = new TreeNode(10);
//        System.out.println(preorderTraversal(preTreeNode)); // [2, 3, 1, 7, 8, 6, 4, 9, 10]
//        System.out.println(postorderTraversal(preTreeNode));
//        System.out.println(isBalanced(preTreeNode));
//        System.out.println(binaryTreePaths(preTreeNode));
//        System.out.println(minDepth(preTreeNode));
//        TreeNode treeNode = new TreeNode(2);
//        treeNode.left = new TreeNode(4);
//        treeNode.right = new TreeNode(6);
//        treeNode.right.left = new TreeNode(8);
//        treeNode.right.left.left = new TreeNode(9);
//        treeNode.right.right = new TreeNode(7);
//        System.out.println(treeNode);
//        System.out.println(minDepth1(treeNode));
        System.out.println(sortedArrayToBST1(new int[]{-1, 3, 4, 5, 7, 8}));
//        System.out.println(rightSideView(depthTree));
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        System.out.println(levelOrder(treeNode));
        System.out.println(hasPathSum(treeNode, 30));
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

    // 中序: 左子树 -> 根节点 -> 右子树
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

    // 一直往下遍历左右树
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return add(root, 0);
    }

    private int add(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        int leftDepth = add(root.left, depth);
        int rightDepth = add(root.right, depth);
        return Math.max(leftDepth, rightDepth);
    }

    // 前序：根 -> 左 -> 右
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    // 后序：左 -> 右 -> 根
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    public boolean isBalanced(TreeNode root) {
        // 记录左右节点往下遍历的最大层数
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.abs(left - right) <= 1;
    }

    /**
     * 输入：root = [1,2,3,null,5] 输出：["1->2->5","1->3"]
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        dfs(root, "", list);
        return list;
    }

    private void dfs(TreeNode root, String path, List<String> list) {
        if (root == null) {
            return;
        }
        path += root.val;
        if (root.right == null && root.left == null) {
            list.add(path);
        } else {
            path += "->";
            dfs(root.right, path, list);
            dfs(root.left, path, list);
        }
    }

    /**
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 这道题递归条件里分为三种情况
        // 1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.left == null && root.right == null) return 1;
        // 2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        // 这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;
        if (root.left == null || root.right == null) return m1 + m2 + 1;

        // 3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可
        return Math.min(m1, m2) + 1;
    }

    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth1(root.left), min_depth); // 1
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth1(root.right), min_depth);
        }
        return min_depth + 1;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode treeNode = new TreeNode();
        for (int num : nums) {
            fill(num, treeNode);
        }
        return treeNode;
    }

    private void fill(int num, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        treeNode.val = num;
    }

    // 2 6 9 11
    // 找出每层最右边的元素，没有则拿左边的
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        f(list, root, 0);
        return list;
    }

    private void f(List<Integer> list, TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        depth++;
        if (node.right != null) {
            list.add(node.right.val);
            f(list, node.right, depth);
        }
        if (node.left != null) {
            list.add(node.left.val);
            f(list, node.left, depth);
        }
    }

    // 声明结果列表
    public List<List<Integer>> list = new ArrayList<>();

    // dfs: 深度优先搜索
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 为空返回
        if (root == null) return list;
        // 开始深度搜索,根节点深度索引为0(递归
        dfs(root, 0);
        return list;
    }

    public void dfs(TreeNode root, int level) {
        // 若开始新一层遍历，创建该层存储列表
        if (list.size() == level) {
            list.add(new ArrayList<>());
        }
        // 递归左子树
        if (root.left != null) {
            dfs(root.left, level + 1);
        }
        // 将当前节点值加入至当前层列表
        list.get(level).add(root.val);
        // 递归右子树
        if (root.right != null) {
            dfs(root.right, level + 1);
        }
    }

    // bfs: 广度优先搜索
    public List<List<Integer>> levelOrder1(TreeNode root) {
        // 结果表
        List<List<Integer>> resList = new ArrayList<>();
        // 为空返回
        if (root == null) return resList;
        // 初始化层队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 遍历树结构
        while (!queue.isEmpty()) {
            // 当前层列表
            List<Integer> list = new ArrayList<>();
            // 记录当前层元素数量
            int levelSize = queue.size();
            // 将当前层数据存储至当前层列表 list 并更新层队列 queue
            for (int i = 0; i < levelSize; i++) {
                TreeNode treeNode = queue.poll();
                list.add(Objects.requireNonNull(treeNode).val);
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            // 当前层元素处理完毕 将当前层列表加入至结果表
            resList.add(list);
        }
        return resList;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 二叉搜索树：
     * 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
     * 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
     * 任意节点的左、右子树也分别为二叉查找树；
     * 平衡树：每个节点的左右两子树高度差都不超过1的二叉树
     */
    // int val;
// TreeNode left;
// TreeNode right;
    public TreeNode sortedArrayToBST1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) return null;

        int i = (end + start) >> 1;
        TreeNode root = new TreeNode(nums[i]);

        root.left = helper(nums, start, i - 1);
        root.right = helper(nums, i + 1, end);

        return root;
    }


}
