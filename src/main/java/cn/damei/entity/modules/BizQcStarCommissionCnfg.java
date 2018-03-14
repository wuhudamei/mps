
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizQcStarCommissionCnfg extends DataEntity2<BizQcStarCommissionCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String isOldNew;
	private String houseType;
	private String starLevel;
	private String commissionAmount;
	private Integer commissionRateMidway;
	private Integer commissionRateComplete;
	private String isEnabled;
	private Double commissionRateMidwayTwo;
	private Double commissionRateCompleteTwo;
	
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