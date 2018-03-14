package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;


public class BalanceFine  implements Serializable{

	
	
	

	private static final long serialVersionUID = 1L;
	private Integer   id;
	private Integer orderId;
	private Integer managerId;
	private String settleCategory;
	private Double settleAmount;
	private String settleStatus;
	private Date settleStatusTime;
	private Integer relatedBussinessId;
	private String remarks;
	private Integer createBy;
	private Integer updateBy;
	private Date createDate;
	private Date updateDate;
	private String  delFlag;
	private String settleRole;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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
