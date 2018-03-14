package cn.damei.entity.mobile.home;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class WeiChatOpenId extends DataEntity2<WeiChatOpenId>{

	private static final long serialVersionUID = 1L;
	
	private String openid;
	private String phone;
	private Date bindDatetime;
	private Date unbindDatetime;
	private String bindStatus;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBindDatetime() {
		return bindDatetime;
	}
	public void setBindDatetime(Date bindDatetime) {
		this.bindDatetime = bindDatetime;
	}
	public Date getUnbindDatetime() {
		return unbindDatetime;
	}
	public void setUnbindDatetime(Date unbindDatetime) {
		this.unbindDatetime = unbindDatetime;
	}
	public String getBindStatus() {
		return bindStatus;
	}
	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}
}
