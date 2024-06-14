package com.example.demo.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author guozixuan
 * @date 2024/6/14 14:32
 */
public class BinaryTest {

    @Test
    public void test() {
        System.out.println(addBinary("101", "111"));
    }


    // 示例 1：
    //输入:a = "11", b = "1"
    //输出："100"
    //
    // 示例 2：
    //输入：a = "1010", b = "1011"
    //输出："10101"
    //
    // 提示：
    // 1 <= a.length, b.length <= 10⁴
    // a 和 b 仅由字符 '0' 或 '1' 组成
    // 字符串如果不是 "0" ，就不含前导零
    //

    public String addBinary(String a, String b) {
        int lengthA = a.length() - 1;
        int lengthB = b.length() - 1;
        StringBuilder result = new StringBuilder();
        int carry = 0;
        while (lengthA >= 0 || lengthB >= 0) {
            int sum = carry;
            if (lengthA >= 0) {
                sum += a.charAt(lengthA) - '0'; // 字符转化为整数
                lengthA--;
            }
            if (lengthB >= 0) {
                sum += b.charAt(lengthB) - '0';
                lengthB--;
            }
            result.append(sum % 2); // sum can be 0, 1, 2, or 3
            carry = sum / 2; // 这一行代码的作用是计算进位值。
            System.out.println("result:" + result + ", sum:" + sum + ", carry:" + carry);
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
