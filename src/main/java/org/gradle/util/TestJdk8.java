package org.gradle.util;

public class TestJdk8  {
	public static void main(String[] args){
		//使用 Lambda 表达式，输出： 16: send email
        start((id, task) -> id + ": " + task);
        //或者
        Machine m1 = (id, task) -> id + ": " + task;
        m1.doSomething(16, "send email");
         
        //使用方法引用，输出： Hello 16: send email
        start(TestJdk8::hello);
        //或者
        Machine m2 = TestJdk8::hello;
        m2.doSomething(16, "send email");
	}
	
	private static void start(Machine machine){
        String result = machine.doSomething(16, "send email");
        System.out.println(result);
    }
     
    public static String hello(int id, String task){
        return "Hello " + id +": " + task;
    }
}
