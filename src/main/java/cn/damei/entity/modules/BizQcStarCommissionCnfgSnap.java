package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 质检员星级提成快照Entity
 * @author 汪文文
 * @version 2017-02-13
 */
public class BizQcStarCommissionCnfgSnap extends DataEntity2<BizQcStarCommissionCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id
	private Integer orderId;		// 订单id
	private Integer pmEmployeeId;		// 质检员员工id
	private String isOldNew;		// 新老房 1新房 0老房
	private String houseType;		// 房屋类型 1平层 2复式 3别墅
	private Integer starLevel;		// 星级
	private Double commissionAmount;		// 提成金额
	private Double commissionRateMidway;		// 中期提成比例
	private Double commissionRateComplete;		// 竣工提成比例
	private String isEnabled;		//  是否启用 1启用 0停用
	
	public BizQcStarCommissionCnfgSnap() {
		super();
	}

	public BizQcStarCommissionCnfgSnap(Integer id){
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
	
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
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
}