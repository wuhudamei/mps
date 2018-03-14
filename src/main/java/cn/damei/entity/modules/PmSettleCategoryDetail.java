
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class PmSettleCategoryDetail extends DataEntity2<PmSettleCategoryDetail> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer pmEmployeeId;
	private String settleCategory;
	private Double settleAmount;
	private String settleStatus;
	private String lastSettleStatus;
	private Date settleStatusTime;
	private String settleRemark;
	private Integer relatedBussinessId;
	private String settleRole;
	private String  relatedBusinessIdVarchar;
	
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