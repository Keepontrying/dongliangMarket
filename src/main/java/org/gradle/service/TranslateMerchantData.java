package org.gradle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.gradle.dao.InsertDataDao;
import org.gradle.dao.TranslateDataDao;
import org.gradle.model.MerchantPrivilege;
import org.gradle.model.PrivilegeDisabled;
import org.gradle.model.UserAndAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;



@Service
public class TranslateMerchantData {
	private static Logger logger=LoggerFactory.getLogger(TranslateMerchantData.class);
	
	@Autowired
	TranslateDataDao translateDataDao;
	@Autowired
	InsertDataDao insertDataDao;
	
	public Map<String,Object> translateDate(boolean bool){
		List<String> owners=translateDataDao.queryOwners();//商户号
		long start = System.currentTimeMillis();
		System.out.println("开始时间+"+start);
		List<PrivilegeDisabled> lists = new ArrayList<PrivilegeDisabled>();
		PrivilegeDisabled disabledtransfer =null;
		PrivilegeDisabled disableddeposit =null;
		PrivilegeDisabled disabledenchashment =null;
		for(String owner : owners){
			List<String> accounts = translateDataDao.queryAccounts(owner);//查询该商户号下面的所有账号
			//确定是否开通冲提转
			for(String account : accounts){
				boolean isOpen = translateDataDao.isOpenAuth(owner,account)>0?false:true;
				if(!isOpen){//关闭权限，写入merchant_privilege_enabled表
					
					disabledtransfer = new PrivilegeDisabled();
					disabledtransfer.setAccount_code(account);
					disabledtransfer.setMerchant_no(owner);
					disabledtransfer.setGroup_code("transfer");
					lists.add(disabledtransfer);
					
					disableddeposit = new PrivilegeDisabled();
					disableddeposit.setAccount_code(account);
					disableddeposit.setMerchant_no(owner);
					disableddeposit.setGroup_code("deposit");
					lists.add(disableddeposit);
					
					disabledenchashment = new PrivilegeDisabled();
					disabledenchashment.setAccount_code(account);
					disabledenchashment.setMerchant_no(owner);
					disabledenchashment.setGroup_code("enchashment");
					lists.add(disabledenchashment);
					
					
					if(lists.size()==999){
						//切换数据库
						System.out.println("插入条数："+lists.size());
						int i=insertDataDao.insertListBeans(lists);
						lists.clear();
					}
					
				}
			}
			
		}
		if(lists.size()!=0){
			//切换数据库
			System.out.println("插入条数："+lists.size());
			System.out.println(Runtime.getRuntime().freeMemory());
			//CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A); 
			insertDataDao.insertListBeans(lists);
			//CustomerContextHolder.clearCustomerType();
		}
		System.out.println("耗时时间："+(System.currentTimeMillis()-start));
        return null;
	}
	//插入默认启用权限
	public boolean  insertDefualtEnabledNode(){
		List<String> list=translateDataDao.findDefualtEnabled();
		return insertDataDao.insertCode(list)>0?true:false;
	}
	//插入商户权限
	public boolean insertMerchantPrivilege(){
		boolean flag=true;
		int start=1;
		while(flag){
			int end=start+1000;
			//List<MerchantPrivilege> cacheList=findMerchantPrivilege(start, end);
			List<MerchantPrivilege> list=translateDataDao.findMerchantPrivilege(start,end);
			if(list==null||list.size()==0)break;
			insertDataDao.insertMerchantPrivilege(list);
			start=end+1;
		}
		
		return false;	}
	//插入用户权限
	
	public boolean insertUserPrivilege(){
		List<UserAndAccount> list=translateDataDao.findUserAndAccount();
		for(UserAndAccount userAndAccount : list){
			if(userAndAccount.getAccountCode()==null||"".equals(userAndAccount.getAccountCode()))continue;
			List<String> codes=translateDataDao.findUserAccountCodes(userAndAccount.getUsername());
			try{
				insertDataDao.insertUserAndCodes(userAndAccount.getLoginame(),userAndAccount.getAccountCode(),codes);
			}catch(Exception e0){
				logger.error(codes.toString());
			}
			
		}
		return false;
	}
	//插入商户权限
	public boolean insertPrivilegeNode(){
	
		
		
		return false;
	}
}
