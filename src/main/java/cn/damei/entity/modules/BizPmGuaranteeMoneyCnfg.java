/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理质保金设置Entity
 * @author wyb
 * @version 2016-12-26
 */
public class BizPmGuaranteeMoneyCnfg extends DataEntity2<BizPmGuaranteeMoneyCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String projectMode;		// 工程模式 -- '
	private Double guaranteeMoneyMax;		// 质保金上限定额 -- '
	private Double guaranteeMoneyPerOrder;		// 每个订单扣除额度 -- '
	
	public BizPmGuaranteeMoneyCnfg() {
		super();
	}

	public BizPmGuaranteeMoneyCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式 -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public Double getGuaranteeMoneyMax() {
		return guaranteeMoneyMax;
	}

	public void setGuaranteeMoneyMax(Double guaranteeMoneyMax) {
		this.guaranteeMoneyMax = guaranteeMoneyMax;
	}
	
	public Double getGuaranteeMoneyPerOrder() {
		return guaranteeMoneyPerOrder;
	}

	public void setGuaranteeMoneyPerOrder(Double guaranteeMoneyPerOrder) {
		this.guaranteeMoneyPerOrder = guaranteeMoneyPerOrder;
	}
	
}