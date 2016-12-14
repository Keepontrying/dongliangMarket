package org.gradle.model;

import java.util.Date;

public class PrivilegeDisabled {
	private Integer id;
	private String merchant_no;
	private String account_code;
	private String group_code;
	private Date create_date;
	private Date modified_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMerchant_no() {
		return merchant_no;
	}
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	public String getAccount_code() {
		return account_code;
	}
	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	@Override
	public String toString() {
		return "PrivilegeDisabled [id=" + id + ", merchant_no=" + merchant_no + ", account_code=" + account_code
				+ ", group_code=" + group_code + ", create_date=" + create_date + ", modified_date=" + modified_date
				+ "]";
	}
	
}
