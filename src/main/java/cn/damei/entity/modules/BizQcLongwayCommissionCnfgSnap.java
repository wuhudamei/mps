/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 质检员远程费快照Entity
 * @author 汪文文
 * @version 2017-02-13
 */
public class BizQcLongwayCommissionCnfgSnap extends DataEntity2<BizQcLongwayCommissionCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id
	private Integer orderId;		// 订单id
	private Integer pmEmployeeId;		// 质检员id
	private Double commissionAmount;		// 远程费金额
	private Double commissionRateMidway;		// 中期提成比例
	private Double commissionRateComplete;		// 竣工提成比例
	private String isEnabled;		// 是否启用
	private Integer starLevel;		// 星级
	
	public BizQcLongwayCommissionCnfgSnap() {
		super();
	}

	public BizQcLongwayCommissionCnfgSnap(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	public Double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public Double getCommissionRateMidway() {
		return commissionRateMidway;
	}

	public void setCommissionRateMidway(Double commissionRateMidway) {
		this.commissionRateMidway = commissionRateMidway;
	}
	
	public Double getCommissionRateComplete() {
		return commissionRateComplete;
	}

	public void setCommissionRateComplete(Double commissionRateComplete) {
		this.commissionRateComplete = commissionRateComplete;
	}
	
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	
}