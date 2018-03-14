/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 自主支配项定义Entity
 * @author wyb
 * @version 2016-12-26
 */
public class BizPmOwnpayCnfg extends DataEntity2<BizPmOwnpayCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String isOldNew;		// 新老房 -- '0.老房；1.新房
	private String projectMode;		// 工程模式 -- '
	private String ownpayName;		// 支配项名称 -- '
	private String unit;		// 单位 -- '
	private Double amount;		// 金额 -- '
	private String remarks;		// 备注 -- '
	private String isEnabled;		// 是否启用 -- '1.启用；0.停用
	
	public BizPmOwnpayCnfg() {
		super();
	}

	public BizPmOwnpayCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	@Length(min=0, max=10, message="工程模式 -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="支配项名称 -- '长度必须介于 0 和 100 之间")
	public String getOwnpayName() {
		return ownpayName;
	}

	public void setOwnpayName(String ownpayName) {
		this.ownpayName = ownpayName;
	}
	
	@Length(min=0, max=10, message="单位 -- '长度必须介于 0 和 10 之间")
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
	
	@Length(min=0, max=500, message="备注 -- '长度必须介于 0 和 500 之间")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}