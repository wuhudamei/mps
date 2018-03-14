package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class SysToken extends DataEntity2<SysToken>{

	private static final long serialVersionUID = 1L;
	private String appType;
	private String phoneNumber;
	private String tokenid;
	private Date validityDatetime;
	
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public Date getValidityDatetime() {
		return validityDatetime;
	}
	public void setValidityDatetime(Date validityDatetime) {
		this.validityDatetime = validityDatetime;
	}
}
