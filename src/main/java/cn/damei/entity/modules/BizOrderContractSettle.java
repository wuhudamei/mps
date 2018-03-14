package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderContractSettle extends DataEntity2<BizOrderContractSettle>{

	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	
	private String settleStage;
	
	private Double packagedSquare;
	
	private Double packagedPrice;
	
	private Double packagedAmount;
	
	private Double contractSubsidySquare;
	
	private Double contractSubsidyPrice;
	
	private Double contractSubsidyAmount;
	
	private Double contractAmount;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getSettleStage() {
		return settleStage;
	}

	public void setSettleStage(String settleStage) {
		this.settleStage = settleStage;
	}

	public Double getPackagedSquare() {
		return packagedSquare;
	}

	public void setPackagedSquare(Double packagedSquare) {
		this.packagedSquare = packagedSquare;
	}

	public Double getPackagedPrice() {
		return packagedPrice;
	}

	public void setPackagedPrice(Double packagedPrice) {
		this.packagedPrice = packagedPrice;
	}

	public Double getPackagedAmount() {
		return packagedAmount;
	}

	public void setPackagedAmount(Double packagedAmount) {
		this.packagedAmount = packagedAmount;
	}

	public Double getContractSubsidySquare() {
		return contractSubsidySquare;
	}

	public void setContractSubsidySquare(Double contractSubsidySquare) {
		this.contractSubsidySquare = contractSubsidySquare;
	}

	public Double getContractSubsidyPrice() {
		return contractSubsidyPrice;
	}

	public void setContractSubsidyPrice(Double contractSubsidyPrice) {
		this.contractSubsidyPrice = contractSubsidyPrice;
	}

	public Double getContractSubsidyAmount() {
		return contractSubsidyAmount;
	}

	public void setContractSubsidyAmount(Double contractSubsidyAmount) {
		this.contractSubsidyAmount = contractSubsidyAmount;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	
}
