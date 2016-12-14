package org.gradle.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RandomNumbers {
/*
 * 1.获取最近10期的号码
 * 2.分析
 * set kj=kaijiang.zhcw.com
 * set ssq=http://%kj%/zhcw/html/ssq
 * down :/list_1.html
 */
  private String url="http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";
  public void get(int pageNum) throws IOException{
		HttpGet get = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		CloseableHttpResponse response=httpclient.execute(get);
		HttpEntity entity=response.getEntity();
		System.out.println(entity.toString());
  }
  
  public static void main(String[] args) throws IOException{
	  RandomNumbers n = new RandomNumbers();
	  n.get(0);
  }
}
