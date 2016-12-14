package org.gradle.gof.singletion;

public class MySingleton {
	/*
	 * 单例模式
	 */
	private static MySingleton singleton = null;
	
	private MySingleton(){
		
	}
	
	public static MySingleton getInstance(){
		if(singleton==null){
			synchronized (MySingleton.class) {
				if(singleton==null){
					singleton=new MySingleton();
				}
			}
		}
		return singleton;
	}
}
