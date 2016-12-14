package org.test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.gradle.model.MerchantPrivilege;
import org.gradle.service.TranslateMerchantData;
import org.gradle.util.LocalCacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:root-context.xml"})
public class GofTest {
	@Autowired
	TranslateMerchantData tranlateService;
	@Autowired
	LocalCacheManager cache;
	
	@Test
	public void testSingleton(){
		//MySingleton singleton=MySingleton.getInstance();
		//System.out.println(singleton);
	}
	@Test
	public void testnum(){
		//tranlateService.translateDate(true);
	}
	@Test
	public void testInsertDefaultEnables(){
		//tranlateService.insertDefualtEnabledNode();
		tranlateService.insertMerchantPrivilege();
		//tranlateService.insertUserPrivilege();
	}
	
	
	@Test
	public void testCache(){
		try {
			List<MerchantPrivilege> str1=cache.findMerhcantPrivileges(1, 0);
			System.err.println(str1);
			List<MerchantPrivilege> str2=cache.findMerhcantPrivileges(1, 0);
			System.err.println(str2);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
