package org.gradle.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class NewCacheManager {
	
	private LoadingCache<String, String> cache=CacheBuilder.newBuilder()
			.maximumSize(1)//最大缓存数据
			.expireAfterWrite(5, TimeUnit.SECONDS)
			.recordStats()
			.build(new CacheLoader<String, String>(){
				@Override
				public String load(String key) throws Exception {
					// TODO Auto-generated method stub
					System.out.println("重新加载");
					String string="auto loading: "+key;
					return string;
				}
			});
	
	public String findFromCache(String key){
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		NewCacheManager cacheManager=new NewCacheManager();
		String str1=cacheManager.findFromCache("key1");
		System.err.println(str1);
		Thread.sleep(10000);
		String str2=cacheManager.findFromCache("key1");
		System.err.println(str2);
		cacheManager.cache.put("key2", "手动刷入缓存");
		long l=cacheManager.cache.stats().hitCount();
		System.err.println(cacheManager.findFromCache("key2"));
		System.err.println(cacheManager.findFromCache("key1"));//key1缓存数据失效，重新刷入缓存
		
		System.err.println(l);
	}
}
