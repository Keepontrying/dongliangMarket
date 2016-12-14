package org.gradle.util;

import java.util.Stack;

public class TestStack {
	static Stack<Integer> stack = new Stack<Integer>();
	public static void digitalSystem(int num){
		//boolean bool=stack.isEmpty();
		int model=num % 8;
		int div=num/8;
		stack.add(model);
		while(div!=0){
			digitalSystem(div);   
			break;
		}
		//return stack;
	}
	
	public static void main(String[] args){
		digitalSystem(10);
		while(!stack.isEmpty())
		System.out.print(stack.pop());
	}
}
