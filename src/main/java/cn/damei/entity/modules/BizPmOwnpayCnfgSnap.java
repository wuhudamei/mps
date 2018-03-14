/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 自主支配快照Entity
 * @author 汪文文
 * @version 2016-12-28
 */
public class BizPmOwnpayCnfgSnap extends DataEntity2<BizPmOwnpayCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private Integer orderId;		// 订单id -- '
	private String isOldNew;		// 新老房 -- '0.老房；1.新房
	private String projectMode;		// 工程模式 -- '
	private String ownpayName;		// 支配项名称 -- '
	private String unit;		// 单位 -- '
	private Double amount;		// 金额 -- '
	private Integer itemManagerId;	//项目经理id
	
	public BizPmOwnpayCnfgSnap() {
		super();
	}

	public BizPmOwnpayCnfgSnap(Integer id){
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
	
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	
}