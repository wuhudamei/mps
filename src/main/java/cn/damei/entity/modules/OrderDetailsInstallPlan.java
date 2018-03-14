package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderDetailsInstallPlan extends DataEntity2<OrderDetailsInstallPlan> {

	private Integer id;


	private Integer orderId;


	private Integer orderInstallItemId;


	private String installItemName;


	private Integer installItemSequence;


	private Date planIntoDate;


	private Date applyIntoDate;


	private Date realIntoDate;


	private Date realCompleteDate;


	private Date realAcceptDate;


	private String status;


	private String applyIntoRemarks;


	private String isCompleteDelay;


	private String completeDelayReason;


	private String completeDelayRemarks;


	private String remarks;


	private Date createDate;


	private Date updateDate;


	private String delFlag;
	

	private Date applyIntoCreateDatetime;
private Date supplierConfirmIntoDate;
	public Date getSupplierConfirmIntoDate() {
	return supplierConfirmIntoDate;
}

public void setSupplierConfirmIntoDate(Date supplierConfirmIntoDate) {
	this.supplierConfirmIntoDate = supplierConfirmIntoDate;
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

	public Integer getOrderInstallItemId() {
		return orderInstallItemId;
	}

	public void setOrderInstallItemId(Integer orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public Integer getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(Integer installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getPlanIntoDate() {
		return planIntoDate;
	}

	public void setPlanIntoDate(Date planIntoDate) {
		this.planIntoDate = planIntoDate;
	}

	public Date getRealIntoDate() {
		return realIntoDate;
	}

	public void setRealIntoDate(Date realIntoDate) {
		this.realIntoDate = realIntoDate;
	}

	public Date getRealCompleteDate() {
		return realCompleteDate;
	}

	public void setRealCompleteDate(Date realCompleteDate) {
		this.realCompleteDate = realCompleteDate;
	}

	public Date getRealAcceptDate() {
		return realAcceptDate;
	}

	public void setRealAcceptDate(Date realAcceptDate) {
		this.realAcceptDate = realAcceptDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyIntoRemarks() {
		return applyIntoRemarks;
	}

	public void setApplyIntoRemarks(String applyIntoRemarks) {
		this.applyIntoRemarks = applyIntoRemarks;
	}

	public String getIsCompleteDelay() {
		return isCompleteDelay;
	}

	public void setIsCompleteDelay(String isCompleteDelay) {
		this.isCompleteDelay = isCompleteDelay;
	}

	public String getCompleteDelayReason() {
		return completeDelayReason;
	}

	public void setCompleteDelayReason(String completeDelayReason) {
		this.completeDelayReason = completeDelayReason;
	}

	public String getCompleteDelayRemarks() {
		return completeDelayRemarks;
	}

	public void setCompleteDelayRemarks(String completeDelayRemarks) {
		this.completeDelayRemarks = completeDelayRemarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Date getApplyIntoCreateDatetime() {
		return applyIntoCreateDatetime;
	}

	public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
		this.applyIntoCreateDatetime = applyIntoCreateDatetime;
	}

}
