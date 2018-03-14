/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 结算类目明细Entity
 * @author wyb
 * @version 2016-10-31
 */
public class PmSettleCategoryDetail extends DataEntity2<PmSettleCategoryDetail> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;//订单id
	private Integer pmEmployeeId;//项目经理id
	private String settleCategory;//分类 '1.标化辅材;2.自主支配;3.中期提成;4.质检罚款;5.竣工提成;6.质保金;
	private Double settleAmount;//结算金额
	private String settleStatus;//结算状态
	private String lastSettleStatus;//更新后的状态
	private Date settleStatusTime;//结算状态日期时间
	private String settleRemark;//结算备注
	private Integer relatedBussinessId;//关联业务id
	private String settleRole; //结算角色 1项目经理  2质检员
	private String  relatedBusinessIdVarchar; //关联业务id 字符类型
	
	public String getRelatedBusinessIdVarchar() {
		return relatedBusinessIdVarchar;
	}
	public void setRelatedBusinessIdVarchar(String relatedBusinessIdVarchar) {
		this.relatedBusinessIdVarchar = relatedBusinessIdVarchar;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}
	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	public String getSettleCategory() {
		return settleCategory;
	}
	public void setSettleCategory(String settleCategory) {
		this.settleCategory = settleCategory;
	}
	public Double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	public String getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	public Date getSettleStatusTime() {
		return settleStatusTime;
	}
	public void setSettleStatusTime(Date settleStatusTime) {
		this.settleStatusTime = settleStatusTime;
	}
	public String getLastSettleStatus() {
		return lastSettleStatus;
	}
	public void setLastSettleStatus(String lastSettleStatus) {
		this.lastSettleStatus = lastSettleStatus;
	}
	public String getSettleRemark() {
		return settleRemark;
	}
	public void setSettleRemark(String settleRemark) {
		this.settleRemark = settleRemark;
	}
	public Integer getRelatedBussinessId() {
		return relatedBussinessId;
	}
	public void setRelatedBussinessId(Integer relatedBussinessId) {
		this.relatedBussinessId = relatedBussinessId;
	}
	public String getSettleRole() {
		return settleRole;
	}
	public void setSettleRole(String settleRole) {
		this.settleRole = settleRole;
	}
	
	
	
}