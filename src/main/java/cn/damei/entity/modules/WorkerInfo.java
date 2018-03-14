/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包详情
 * @author 张康健
 * @version 2017-3-25
 */
public class WorkerInfo extends DataEntity2<WorkerInfo> {
	
	private static final long serialVersionUID = 1L;
	private String isLeader;
	private String realName;
	private String phone;
	private String paymentAmount;
	public String getIsLeader() {
		if(isLeader!=null&&isLeader.equals("1")){
			return "组长";
		}else{
			return "工人";
		}
	}
	public void setIsLeader(String isLeader) {
	
		this.isLeader = isLeader;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public WorkerInfo() {
		super();
	}
	
	
	
	
}