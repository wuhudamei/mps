package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 自主支配项Entity
 */
public class Ownpay extends DataEntity2<Ownpay> {
	
	private static final long serialVersionUID = 1L;
	private Integer pmSettleBillId;		// 结算单id
	private Integer pmSettleCategorySummaryId;		// 结算单汇总表id
	private Integer pmSettleCategoryDetailsId;		// 结算单汇总表id
	private Integer orderId;		// 订单id -- '
	private String ownpayName;		// 支配项名称 -- '
	private String unit;		// 单位 -- '
	private Double amount;		// 金额 -- '
	private String remarks;		// 备注 -- '
	
	
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