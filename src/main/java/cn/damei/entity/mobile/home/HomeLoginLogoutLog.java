package cn.damei.entity.mobile.home;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class HomeLoginLogoutLog extends DataEntity2<HomeLoginLogoutLog> {

	private static final long serialVersionUID = 1L;
	

	private String dealMode;

	private String dealType;

	private String dealPhone;
	

	private Date dealTime;
	private String beginDeal;
	private String endDeal;
	
	public String getDealMode() {
		return dealMode;
	}
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getDealPhone() {
		return dealPhone;
	}
	public void setDealPhone(String dealPhone) {
		this.dealPhone = dealPhone;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getBeginDeal() {
		return beginDeal;
	}
	public void setBeginDeal(String beginDeal) {
		this.beginDeal = beginDeal;
	}
	public String getEndDeal() {
		return endDeal;
	}
	public void setEndDeal(String endDeal) {
		this.endDeal = endDeal;
	}
}
