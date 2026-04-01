package stack;

import java.util.*;

public class min_optimized {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    min_optimized() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
        if (minStack.isEmpty() || minStack.peek() >= value) {
            minStack.push(value);
        }
    }

    public void pop() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack is Empty!");
        if (stack.peek().equals(minStack.peek()))
            minStack.pop();
        stack.pop();
    }

    public int getMin() {
        if (minStack.isEmpty())
            throw new RuntimeException("Stack Underflow!");
        return minStack.peek();
    }
}
