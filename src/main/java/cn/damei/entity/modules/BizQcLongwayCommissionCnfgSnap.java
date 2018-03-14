
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;


public class BizQcLongwayCommissionCnfgSnap extends DataEntity2<BizQcLongwayCommissionCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer orderId;
	private Integer pmEmployeeId;
	private Double commissionAmount;
	private Double commissionRateMidway;
	private Double commissionRateComplete;
	private String isEnabled;
	private Integer starLevel;
	
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