package com.algorhythms.stack;

public class StackClient {

	public static void main(String[] args) {
		//Stack<String> stack = new ResizingArrayStack<>();
		Stack<String> stack = new LinkedStack<>();
		stack.push("Test1");
		stack.pop();
		
		stack.push("Test2");
		stack.push("Test3");
		stack.push("Test4");
		stack.push("Test5");
		stack.push("Test6");
		stack.push("Test7");
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		stack.pop();
		stack.pop();
		stack.pop();
		for(String s : stack) {
			System.out.println(s);
		}
	}
}
