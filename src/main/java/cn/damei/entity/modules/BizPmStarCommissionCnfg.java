/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理星级和提成设置Entity
 * @author wyb
 * @version 2016-12-24
 */
public class BizPmStarCommissionCnfg extends DataEntity2<BizPmStarCommissionCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String isOldNew;		// 新老房 -- '0.老房；1.新房
	private Integer starLevel;		// 星级 -- '
	private Double commissionAmount;		// 提成金额 -- '
	private Integer commissionRateMidway;		// 中期提成比例 -- '页面展示
	private Integer commissionRateComplete;		// 竣工提成比例 -- '页面展示
	private String isEnabled;		// 是否启用 -- '1.启用；0.停用
	
	private Double commissionRateMidwayTwo;		// 中期提成比例 -- '数据库存储
	private Double commissionRateCompleteTwo;		// 竣工提成比例 -- '数据库存储
	
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

	public BizPmStarCommissionCnfg() {
		super();
	}

	public BizPmStarCommissionCnfg(Integer id){
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
	
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
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
	

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}