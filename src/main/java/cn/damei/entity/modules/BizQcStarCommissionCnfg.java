/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检员星级和提成设置Entity
 * @author wyb
 * @version 2017-02-13
 */
public class BizQcStarCommissionCnfg extends DataEntity2<BizQcStarCommissionCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private String isOldNew;		// 新老房
	private String houseType;		// 房屋类型
	private String starLevel;		// 星级
	private String commissionAmount;		// 提成金额
	private Integer commissionRateMidway;		// 中期提成比例
	private Integer commissionRateComplete;		// 竣工提成比例
	private String isEnabled;		// 是否启用
	private Double commissionRateMidwayTwo;		// 中期提成比例
	private Double commissionRateCompleteTwo;		// 竣工提成比例
	
	public BizQcStarCommissionCnfg() {
		super();
	}

	public BizQcStarCommissionCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="新老房长度必须介于 0 和 10 之间")
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	@Length(min=0, max=10, message="房屋类型长度必须介于 0 和 10 之间")
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
	@Length(min=0, max=11, message="星级长度必须介于 0 和 11 之间")
	public String getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	
	public String getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(String commissionAmount) {
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