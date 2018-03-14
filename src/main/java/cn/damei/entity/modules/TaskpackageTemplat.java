package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class TaskpackageTemplat extends DataEntity2<TaskpackageTemplat>{

	private static final long serialVersionUID = 1L;
	private String no;
	private String templatName;
	private String taskPackageTypeId;
	private Integer storeId;
	private String projectMode;
	private String storeOrder;
	private String advancePaymentRates;
	private String restPaymentRates;
	private String status;
	private String isQualityGuarantee;
	private String qualityGuaranteeRate;
	private String settleStyle;
	
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTemplatName() {
		return templatName;
	}
	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}
	public String getTaskPackageTypeId() {
		return taskPackageTypeId;
	}
	public void setTaskPackageTypeId(String taskPackageTypeId) {
		this.taskPackageTypeId = taskPackageTypeId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreOrder() {
		return storeOrder;
	}
	public void setStoreOrder(String storeOrder) {
		this.storeOrder = storeOrder;
	}
	public String getAdvancePaymentRates() {
		return advancePaymentRates;
	}
	public void setAdvancePaymentRates(String advancePaymentRates) {
		this.advancePaymentRates = advancePaymentRates;
	}
	public String getRestPaymentRates() {
		return restPaymentRates;
	}
	public void setRestPaymentRates(String restPaymentRates) {
		this.restPaymentRates = restPaymentRates;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsQualityGuarantee() {
		return isQualityGuarantee;
	}
	public void setIsQualityGuarantee(String isQualityGuarantee) {
		this.isQualityGuarantee = isQualityGuarantee;
	}
	public String getQualityGuaranteeRate() {
		return qualityGuaranteeRate;
	}
	public void setQualityGuaranteeRate(String qualityGuaranteeRate) {
		this.qualityGuaranteeRate = qualityGuaranteeRate;
	}
	public String getSettleStyle() {
		return settleStyle;
	}
	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
}
