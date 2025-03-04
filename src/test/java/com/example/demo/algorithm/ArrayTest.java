package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guozixuan
 * @date 2024/6/14 14:27
 */
public class ArrayTest {

    @Test
    public void test() {
//        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
//        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
//        System.out.println(lengthOfLastWord("the last american generation  "));
//        System.out.println(Arrays.toString(plusOne(new int[]{9}))); // wrong answer, 没考虑到进位情况
//        System.out.println(Arrays.toString(plusOne1(new int[]{9,8,9})));
//        System.out.println(maxProfit(new int[]{7,1,5,3,6,4})); // 长度很大超时。。。
//        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(permute(new int[]{1, 2, 3, 4}));
//        System.out.println(maxProfitSecondVersion(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(maxProfitSecondVersion1(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(maxProfitThirdVersion(new int[]{1, 2, 4, 7, 11})); // wrong answer，可以第一天买最后一天卖，该答案只考虑了两天情况
//        System.out.println(maxProfitThirdVersion1(new int[]{1, 2, 3, 4, 5}));
//        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}})); // wrong answer， 非螺旋而是顺时针逆时针。。
//        System.out.println(spiralOrder1(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
//        System.out.println(Arrays.deepToString(generateMatrix(3)));
//        System.out.println(hIndex(new int[]{3, 0, 6, 1, 5})); // 0 1 3 5 6
        System.out.println(minimumAbsDifference(new int[]{1, 3, 6, 10, 15}));

    }

    private String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty()) {
                return "";
            }
        }
        return prefix;
    }

    /**
     * 示例 1：
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2,_,_]
     * 解释：你的函数函数应该返回 k = 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3,_,_,_]
     * 解释：你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4。
     * 注意这五个元素可以任意顺序返回。
     */
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return k;
    }

    public int lengthOfLastWord(String s) {
        char[] array = s.toCharArray();
        int result = 0;
        boolean endFlag = true;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == ' ' && endFlag) {
                continue;
            }
            if (array[i] != ' ') {
                result++;
                endFlag = false;
            } else {
                break;
            }
        }
        return result;
    }

    // GPT optimise: 不转化为数组，空间复杂度 O(n) -> O(1)
    public int lengthOfLastWord1(String s) {
        int length = 0;
        boolean endFlag = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (endFlag) {
                    continue;
                } else {
                    break;
                }
            } else {
                length++;
                endFlag = false;
            }
        }
        return length;
    }

    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        return digits;
    }

    // 989
    public int[] plusOne1(int[] digits) {
//        int total = 0;
        // 1,2,3
//        for (int i = digits.length - 1; i >= 0; i--) {
        // 通过乘10的个数算到总的值
//            total = total + digits[i] * ;
//        }
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10; // **core 9+1 % 10 等于0，走下一次循环。如果不等于0，说明正常累加了，直接返回数组即可
            System.out.println("i:" + i + ", digits[i]:" + digits[i]);
            System.out.println(digits[i] % 10);
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 9999 的特殊情况
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    // 最大利息
    // 输入：[7,1,5,3,6,4]，输出：5（6-1）
    // 选择一个作为买入值，拿后面的减去该值，获取最大利息
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                System.out.println("i: " + i + ", j: " + j);
                if (prices[i] > prices[j]) {
                    continue;
                }
                System.out.println("price: [j] = " + prices[j]);
                result = Math.max(result, prices[j] - prices[i]);
                System.out.println("result " + result);
            }
        }
        return result;
    }

    /**
     * 解释2,6,1,4这样的情况
     * 简单来说:是如下的情况(实际会比较的多一点,但是不影响总体)
     * 首先,minPrice = 2 / profit = 6-2 = 4
     * 然后找到一个更小的minPrice = 1  之后计算出来的profit = 4-1 = 3
     * <p>
     * 最后比较两个profit,谁更大就取谁!  这才是关键,谁更大就取谁!
     * <p>
     * 等于将股价分为多段--->
     * 条件:要求每段都是升序,并且后面的价格段中minPrice比前面的minPrice更小,后面的价格段以minPrice开头
     * <p>
     * 例如3,100,2,99,1,90 --> 这样算法就将这段数字分为三段,[3,100] [2,99] [1,90],
     * 然后分别计算他们的profit,最后比较它们的大小,取最大的profit,返回
     * <p>
     * 换个例子,如果是这样的情况:[7,2,10,3,1,40]
     * 那么就分为[7,2,10,3] [1,40]
     * 第一段是最佳profit是8, 第二段的最佳profit是39,最后比较它们,取更大值39
     * <p>
     * 这即为动态规划的思路,求解多段问题,记录前面的最优解,然后与现在的最优解进行比较交换,最后剩下的就是最优解答案
     */
    public int maxProfit1(int[] prices) {
        // 如果数组为空或长度为0，直接返回0
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE; // 初始化一个极大值，表示股票的最低价格
        int maxProfit = 0; // 初始化最大利润为0

        // 遍历所有股票价格
        for (int price : prices) {
            // 如果当前价格比minPrice小，更新minPrice
            if (price < minPrice) {
                minPrice = price; // 7 2
            }
            // 否则，计算当前价格卖出时的利润，并更新maxProfit
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice; // 10-1
            }
        }
        return maxProfit; // 返回最大利润
    }

    /**
     * 示例 1：
     * 输入: s = "A man, a plan, a canal: Panama"
     * 输出：true
     * 解释："amanaplanacanalpanama" 是回文串。
     */
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.trim().isEmpty()) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        System.out.println(sb);
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 全排列 dizzy
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] 2*3
     * 1234 1243 1324 1342 1423 1432 6*4
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    continue; // 已经包含的数字不再添加
                }
                tempList.add(num);
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void testTwoDimension() {
        int[][] num = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};    //创建二维数组并赋值
        for (int i = 0; i < num.length; i++) {             //for循环遍历输出数组
            for (int j = 0; j < num[i].length; j++) {
                System.out.print(num[i][j] + " ");
            }
        }
    }

    public int maxProfitSecondVersion(int[] prices) {
        int right = 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[right] < prices[i]) {
                profit += prices[i] - prices[right];
            }
            right++;
        }
        return profit;
    }

    // 动态规划
    public int maxProfitSecondVersion1(int[] prices) {
        int[] dp = new int[prices.length + 1];//这里从第0天开始，到底i天
        dp[0] = 0;//第0天没有股票，最大利润为0
        dp[1] = 0;//第一天只能买，不能买，因此最大利润也是0
        for (int i = 1; i < prices.length; i++) {
            int A = dp[i] + prices[i] - prices[i - 1];//第一种选择
            int B = dp[i];//第二种选择
            dp[i + 1] = Math.max(A, B);//i从0开始，所以dp[I＋1]是当前天数
        }
        return dp[prices.length];
    }

    // 1 9 8 6 4

    // 两笔 1 2 4 7 11
    // 1,2,3,4,5
    public int maxProfitThirdVersion(int[] prices) {
        int left = 0;
        int first = 0, second = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[left] < prices[i]) {
                int increment = prices[i] - prices[left];
                second = Math.min(first, increment);
                first = Math.max(first, increment);
            }
            left++;
        }
        return first + second;
    }

    public int maxProfitThirdVersion1(int[] prices) {
        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);            // 买入价格最小化
            firstSell = Math.max(firstSell, firstBuy + price); // 卖出后的利润最大化
            secondBuy = Math.max(secondBuy, firstSell - price); // 第二次买入后的净利润最大化
            secondSell = Math.max(secondSell, secondBuy + price); // 第二次卖出后的利润最大化
            System.out.println("firstBuy: " + firstBuy + ", firstSell: " + firstSell + ", secondBuy: " + secondBuy + ", secondSell: " + secondSell);
        }

        return secondSell;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> resultList = new ArrayList<>();
        if (matrix == null) {
            return resultList;
        }
        // 基数从左往右，偶数从右往左
        boolean inorderFlag = true;
        for (int i = 0; i < matrix.length; i++) {
            int[] array = matrix[i];
            if (inorderFlag) {
                for (int i1 = 0; i1 < array.length; i1++) {
                    resultList.add(array[i1]);
                }
                inorderFlag = false;
            } else {
                for (int i1 = array.length - 1; i1 >= 0; i1--) {
                    resultList.add(array[i1]);
                }
                inorderFlag = true;
            }
        }
        return resultList;
    }

    private List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // 从左到右遍历上边界
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // 上边界下移

            // 从上到下遍历右边界
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // 右边界左移

            // 确保还有行和列可以遍历
            if (top <= bottom) {
                // 从右到左遍历下边界
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // 下边界上移
            }

            if (left <= right) {
                // 从下到上遍历左边界
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // 左边界右移
            }
        }

        return result;
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // 从左到右填充上边界
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++; // 上边界下移

            // 从上到下填充右边界
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--; // 右边界左移

            // 从右到左填充下边界
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = num++;
                }
                bottom--; // 下边界上移
            }

            // 从下到上填充左边界
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++; // 左边界右移
            }
        }

        return matrix;
    }

    // 0 1 3 5 6
    public int hIndex(int[] citations) {
        int length = citations.length;
        Arrays.sort(citations);
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            System.out.println("length-i: " + (length - i));
            if (citations[i] >= length - i) {
                count++;
            }
        }
        return count;
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int min = Integer.MIN_VALUE;
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i - 1] - arr[i];
            System.out.println(diff);
            if (diff > min) {
                min = diff;
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i - 1] - arr[i];
            if (diff == min) {
                ArrayList<Integer> item = new ArrayList<>();
                item.add(arr[i - 1]);
                item.add(arr[i]);
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 第一层 i=0 j 0~n-1   j=n-1 i=0~m-1  i=m-1 j=n-1~0 j=0 i=m-1~0
     * rows1=0   cols1=0   cols2=n-1  rows2=m-1
     * rows1++   cols++    cols2--    rows2--
     * <p>
     * 终止条件：
     * rows1>rows2  cols1>cols2
     **/
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ret = new ArrayList();
        int m = matrix.length;
        int n = matrix[0].length;
        int rows1 = 0;
        int rows2 = m - 1;
        int cols1 = 0;
        int cols2 = n - 1;

        //避免添加重复元素，遍历过的元素值设置成一个固定值
        int noVal = -100;
        System.out.println("m:" + m + ";n:" + n);
        while (rows1 <= rows2 && cols1 <= cols2) {
            //向右
            for (int j = cols1; j <= cols2; j++) {
                if (matrix[rows1][j] != noVal) {
                    ret.add(matrix[rows1][j]);
                    matrix[rows1][j] = noVal;
                }
            }
            rows1++;

            //向下
            for (int i = rows1; i <= rows2; i++) {
                if (matrix[i][cols2] != noVal) {
                    ret.add(matrix[i][cols2]);
                    matrix[i][cols2] = noVal;
                }
            }
            cols2--;

            //向左
            for (int j = cols2; j >= cols1; j--) {
                if (matrix[rows2][j] != noVal) {
                    ret.add(matrix[rows2][j]);
                    matrix[rows2][j] = noVal;
                }
            }
            rows2--;

            //向上
            for (int i = rows2; i >= rows1; i--) {
                if (matrix[i][cols1] != noVal) {
                    ret.add(matrix[i][cols1]);
                    matrix[i][cols1] = noVal;
                }
            }
            cols1++;

            System.out.println("rows1:" + rows1 + ";rows2:" + rows2 + ";cols1:" + cols1 + ";cols2:" + cols2);
        }

        return ret;
    }


}
