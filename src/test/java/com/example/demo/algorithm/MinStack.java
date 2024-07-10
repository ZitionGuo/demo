package com.example.demo.algorithm;

import java.util.Stack;

/**
 * @author guozixuan
 * @date 2024/7/10 14:48
 */
public class MinStack {

    private Stack<Integer> stack;

    public MinStack() {
    }

    public void push(int val) {
        this.stack.push(val);
    }

    public void pop() {
        this.stack.pop();
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (Integer integer : stack) {
            min = Math.min(integer, min);
        }
        return min;
    }
}

class MinStack1 {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        //当前值更小
        if(x <= min){
            //将之前的最小值保存
            stack.push(min);
            //更新最小值
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        //如果弹出的值是最小值，那么将下一个元素更新为最小值
        if(stack.pop() == min) {
            min=stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

class MinStack2 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }
    public void pop() {
        if(stack.pop().equals(minStack.peek()))
            minStack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }
}


