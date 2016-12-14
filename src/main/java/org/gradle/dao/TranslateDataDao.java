package org.gradle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gradle.model.MenuInfo;
import org.gradle.model.MerchantPrivilege;
import org.gradle.model.UserAndAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateDataDao {
	List<String> queryOwners();
	List<String> queryAccounts(@Param (value = "owner") String owner);
	int isOpenAuth(@Param (value = "owner") String owner, @Param (value = "account")  String account);
	List<String> findDefualtEnabled();
	List<MerchantPrivilege> findMerchantPrivilege(@Param (value = "start") int start, @Param (value = "end") int end);
	List<UserAndAccount> findUserAndAccount();
	List<String> findUserAccountCodes(@Param (value = "username")String loginame);
	List<MenuInfo> findAllMenuInfo();
	
}
 