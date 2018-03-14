package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单承包结算实体类
 * @author hyh
 *
 */
public class BizOrderContractSettle extends DataEntity2<BizOrderContractSettle>{

	private static final long serialVersionUID = 1L;
	
	private Integer orderId; //订单Id
	
	private String settleStage; //承包结算阶段  10:中期结算  20:竣工结算
	
	private Double packagedSquare;//套餐计价面积
	
	private Double packagedPrice; //套餐单价
	
	private Double packagedAmount; //套餐总价
	
	private Double contractSubsidySquare;//承包补助面积
	
	private Double contractSubsidyPrice;//承包补助单价
	
	private Double contractSubsidyAmount;//承包补助总金额
	
	private Double contractAmount;//承包总金额    =  套餐总价+承包补助总金额

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
