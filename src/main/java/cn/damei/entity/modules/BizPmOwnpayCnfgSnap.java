
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;


public class BizPmOwnpayCnfgSnap extends DataEntity2<BizPmOwnpayCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer orderId;
	private String isOldNew;
	private String projectMode;
	private String ownpayName;
	private String unit;
	private Double amount;
	private Integer itemManagerId;
	
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