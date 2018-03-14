package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单材料搬运运输费实体类
 * @author hyh
 *
 */
public class BizOrderMaterialCarryCost extends DataEntity2<BizOrderMaterialCarryCost>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	
	private Double carryCostAmount;
	
	private Double accountRate;
	
	private Double accountAmount;
	
	private String carryCostRemarks;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getCarryCostAmount() {
		return carryCostAmount;
	}

	public void setCarryCostAmount(Double carryCostAmount) {
		this.carryCostAmount = carryCostAmount;
	}

	public Double getAccountRate() {
		return accountRate;
	}

	public void setAccountRate(Double accountRate) {
		this.accountRate = accountRate;
	}

	public Double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getCarryCostRemarks() {
		return carryCostRemarks;
	}

	public void setCarryCostRemarks(String carryCostRemarks) {
		this.carryCostRemarks = carryCostRemarks;
	}
	
	

}
