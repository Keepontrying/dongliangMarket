package org.gradle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gradle.model.MerchantPrivilege;
import org.gradle.model.PrivilegeDisabled;
import org.gradle.util.TargetSource;
import org.springframework.stereotype.Repository;

@Repository
public interface InsertDataDao {

	int insertListBeans(@Param (value="beans") List<PrivilegeDisabled> lists);

	int insertCode(@Param (value="code") List<String> list);
	@TargetSource("dataSource2")
	int insertMerchantPrivilege(@Param(value="beans") List<MerchantPrivilege> list);

	void insertUserAndCodes(@Param(value="loginame")String loginame, @Param(value="account")String string, @Param(value="codes")List<String> codes);

}
