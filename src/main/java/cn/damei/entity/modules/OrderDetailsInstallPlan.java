package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderDetailsInstallPlan extends DataEntity2<OrderDetailsInstallPlan> {

	private Integer id;

	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 订单安装项id
	 */
	private Integer orderInstallItemId;

	/**
	 * 安装项名称
	 */
	private String installItemName;

	/**
	 * 安装项顺序
	 */
	private Integer installItemSequence;

	/**
	 * 计划进场日期
	 */
	private Date planIntoDate;

	/**
	 * 申请进场日期
	 */
	private Date applyIntoDate;

	/**
	 * 实际进场日期
	 */
	private Date realIntoDate;

	/**
	 * 实际完工日期
	 */
	private Date realCompleteDate;

	/**
	 * 实际验收日期
	 */
	private Date realAcceptDate;

	/**
	 * 状态 1.已生成计划；2.已申请计划；3.已验收
	 */
	private String status;

	/**
	 * 申请进场备注
	 */
	private String applyIntoRemarks;

	/**
	 * 是否完工延期 0.否；1.是
	 */
	private String isCompleteDelay;

	/**
	 * 完工延期原因 1.发生变更；2.材料未送到；3.工人不够；4.物业不让施工；5.其他
	 */
	private String completeDelayReason;

	/**
	 * 完工延期描述
	 */
	private String completeDelayRemarks;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建日期时间
	 */
	private Date createDate;

	/**
	 * 更新日期时间
	 */
	private Date updateDate;

	/**
	 * 是否删除
	 */
	private String delFlag;
	
	/**
	 * 申请进场创建期时间 
	 */
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
