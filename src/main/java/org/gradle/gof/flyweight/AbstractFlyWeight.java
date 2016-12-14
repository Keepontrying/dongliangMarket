package org.gradle.gof.flyweight;

import java.io.File;
import java.util.Random;

public abstract class AbstractFlyWeight {
	/*
	 * 蝇量模式：运用共享技术有效的支持大量细粒度的对象
	 * 共享对象的内部状态，管理外部状态
	 */
	public void test(String pathname){
		File file = new java.io.File(pathname);
		
	}
	
	public static void main(String[] args){
		while(5>3){
			System.out.println(2);
			//break;
		}
	}
}
