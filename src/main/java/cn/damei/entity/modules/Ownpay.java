package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class Ownpay extends DataEntity2<Ownpay> {
	
	private static final long serialVersionUID = 1L;
	private Integer pmSettleBillId;
	private Integer pmSettleCategorySummaryId;
	private Integer pmSettleCategoryDetailsId;
	private Integer orderId;
	private String ownpayName;
	private String unit;
	private Double amount;
	private String remarks;
	
	
	public Integer getPmSettleBillId() {
		return pmSettleBillId;
	}
	public void setPmSettleBillId(Integer pmSettleBillId) {
		this.pmSettleBillId = pmSettleBillId;
	}
	public Integer getPmSettleCategorySummaryId() {
		return pmSettleCategorySummaryId;
	}
	public void setPmSettleCategorySummaryId(Integer pmSettleCategorySummaryId) {
		this.pmSettleCategorySummaryId = pmSettleCategorySummaryId;
	}
	public Integer getPmSettleCategoryDetailsId() {
		return pmSettleCategoryDetailsId;
	}
	public void setPmSettleCategoryDetailsId(Integer pmSettleCategoryDetailsId) {
		this.pmSettleCategoryDetailsId = pmSettleCategoryDetailsId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOwnpayName() {
		return ownpayName;
	}
	public void setOwnpayName(String ownpayName) {
		this.ownpayName = ownpayName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	
}