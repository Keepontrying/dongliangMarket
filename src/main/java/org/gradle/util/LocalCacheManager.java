package org.gradle.util;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.gradle.dao.TranslateDataDao;
import org.gradle.model.MerchantPrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache; 

@Component
public class LocalCacheManager {
	@Autowired
	TranslateDataDao dao;
	/*
	 * CacheBuilder缓存机制
	 */
	private LoadingCache<Integer,List<MerchantPrivilege>> cache = CacheBuilder.newBuilder()
			.maximumSize(1000)
			.recordStats()
			.expireAfterWrite(10, TimeUnit.MINUTES)
			.build(new CacheLoader<Integer, List<MerchantPrivilege>>() {
				public List<MerchantPrivilege> load(Integer key){
					System.out.println("重新加载");
					return loadKey(key);
				}

				private List<MerchantPrivilege> loadKey(Integer key) {
					// TODO Auto-generated method stub
					return dao.findMerchantPrivilege(key, key+2);
				}
			});
	
	public List<MerchantPrivilege> findMerhcantPrivileges(int start,int end) throws ExecutionException{
		
		ConcurrentMap<Integer, List<MerchantPrivilege>> map=cache.asMap();
		return cache.get(start);
	}
	
}
