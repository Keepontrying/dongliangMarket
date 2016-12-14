package org.gradle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceAop {
	private static Logger logger=LoggerFactory.getLogger(DataSourceAop.class);
	public void switchDataSource(){
		logger.info("本地数据库插入数据");
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A); 
	}
	
	public void clearDataSource(){
		logger.info("清除数据源");
		CustomerContextHolder.clearCustomerType();
	}
}
