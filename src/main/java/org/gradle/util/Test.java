package org.gradle.util;

import java.io.Serializable;

public class Test implements Runnable  ,Serializable
{  
    private int static_i;//静态变量   非线程安全
      
    public void run()  
    {  
        static_i = 4;  
        System.out.println("[" + Thread.currentThread().getName()  
                + "]获取static_i 的值:" + static_i);  
        static_i = 10;  
        System.out.println("[" + Thread.currentThread().getName()  
                + "]获取static_i*3的值:" + static_i * 2);  
    }  
      
    public static void main(String[] args)  
    {  
        Test t = new Test();  
        //启动尽量多的线程才能很容易的模拟问题   
        for (int i = 0; i < 300; i++)  
        {  
            //t可以换成new Test(),保证每个线程都在不同的对象中执行，结果一样   
            new Thread(t, "线程" + i).start();  
        }  
    }  
}  
