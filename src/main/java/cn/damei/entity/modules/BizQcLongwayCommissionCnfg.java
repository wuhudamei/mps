/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 远程费提成金额设置Entity
 * @author wyb
 * @version 2017-02-13
 */
public class BizQcLongwayCommissionCnfg extends DataEntity2<BizQcLongwayCommissionCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private Double commissionAmount;		// 远程费金额
	private Integer commissionRateMidway;		// 中期提成比例
	private Integer commissionRateComplete;		// 竣工提成比例
	private String isEnabled;		// 是否启用
	
	private Double commissionRateMidwayTwo;		// 中期提成比例
	private Double commissionRateCompleteTwo;		// 竣工提成比例

	public BizQcLongwayCommissionCnfg() {
		super();
	}

	public BizQcLongwayCommissionCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public Integer getCommissionRateMidway() {
		return commissionRateMidway;
	}

	public void setCommissionRateMidway(Integer commissionRateMidway) {
		this.commissionRateMidway = commissionRateMidway;
	}
	
	public Integer getCommissionRateComplete() {
		return commissionRateComplete;
	}

	public void setCommissionRateComplete(Integer commissionRateComplete) {
		this.commissionRateComplete = commissionRateComplete;
	}
	
	@Length(min=0, max=1, message="是否启用长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Double getCommissionRateMidwayTwo() {
		return commissionRateMidwayTwo;
	}

	public void setCommissionRateMidwayTwo(Double commissionRateMidwayTwo) {
		this.commissionRateMidwayTwo = commissionRateMidwayTwo;
	}

	public Double getCommissionRateCompleteTwo() {
		return commissionRateCompleteTwo;
	}

	public void setCommissionRateCompleteTwo(Double commissionRateCompleteTwo) {
		this.commissionRateCompleteTwo = commissionRateCompleteTwo;
	}
	
	
}