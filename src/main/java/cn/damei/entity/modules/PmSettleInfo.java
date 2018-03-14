package cn.damei.entity.modules;

import java.util.Date;


public class PmSettleInfo {

	private String orderNum;
	
	private String itemManager;
	
	private Integer settleBillType;
	
	private String createMonthlyDate;
	
	private Double settleAmount;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getSettleBillType() {
		return settleBillType;
	}

	public void setSettleBillType(Integer settleBillType) {
		this.settleBillType = settleBillType;
	}

	public String getCreateMonthlyDate() {
		return createMonthlyDate;
	}

	public void setCreateMonthlyDate(String createMonthlyDate) {
		this.createMonthlyDate = createMonthlyDate;
	}

	public Double getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	
}
