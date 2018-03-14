package cn.damei.entity.mobile.home;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 
 * <dl>
 * <dd>Description: 业主APP登录登出日志</dd>
 * <dd>Company: 大城若谷信息技术有限公司</dd>
 * <dd>@date：2017年8月31日 下午7:54:46</dd>
 * <dd>@author：Li wancai</dd>
 * </dl>
 */
public class HomeLoginLogoutLog extends DataEntity2<HomeLoginLogoutLog> {

	private static final long serialVersionUID = 1L;
	
	//登录方式（wechat、app）
	private String dealMode;
	//登录类型(in、out)
	private String dealType;
	//登录手机号
	private String dealPhone;
	
	//登录登出时间
	private Date dealTime;
	private String beginDeal;		// 开始时间
	private String endDeal;		// 结束时间
	
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
