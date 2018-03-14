/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 自主支配日志表Entity
 * @author wyb
 * @version 2017-02-15
 */
public class BizPmOwnpayLog extends DataEntity2<BizPmOwnpayLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private Integer orderId;		// 订单
	private String isOldNew;		// 新老房
	private String projectMode;		// 工程模式
	private String ownpayName;		// ֧支配项名称
	private String unit;		// 单位
	private Double amount;		// 金额
	
	public BizPmOwnpayLog() {
		super();
	}

	public BizPmOwnpayLog(Integer id){
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
	
	@Length(min=0, max=10, message="新老房长度必须介于 0 和 10 之间")
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="֧支配项名称长度必须介于 0 和 100 之间")
	public String getOwnpayName() {
		return ownpayName;
	}

	public void setOwnpayName(String ownpayName) {
		this.ownpayName = ownpayName;
	}
	
	@Length(min=0, max=10, message="单位长度必须介于 0 和 10 之间")
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
	
}