
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsChoiceBill extends DataEntity2<BizMaterialsChoiceBill> {

	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String orderNumber;
	private Double materialsChoiceTotalAmount;

	private Integer storeId;
	private String engineDepartId;
	private String projectMode;
	private String counts;
	private String isFalg;
	private String customerName;
	private String isdealedMain;
	private String isdealedWallFloor;
	private String customerPhone;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String orderDesignerName;
	private String orderDesignerPhone;
	private String itemManager;
	private String storeName;
	private String contractNumber;
	private Integer changeBillCount;
	private Double wallFloorTileSquareBudget;

	private String status;

	public BizMaterialsChoiceBill() {
		super();
	}

	public BizMaterialsChoiceBill(Integer id) {
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 100, message = "订单编号长度必须介于 0 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getMaterialsChoiceTotalAmount() {
		return materialsChoiceTotalAmount;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getIsFalg() {
		return isFalg;
	}

	public void setIsFalg(String isFalg) {
		this.isFalg = isFalg;
	}

	public void setMaterialsChoiceTotalAmount(Double materialsChoiceTotalAmount) {
		this.materialsChoiceTotalAmount = materialsChoiceTotalAmount;
	}

	public String getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getIsdealedMain() {
		return isdealedMain;
	}

	public void setIsdealedMain(String isdealedMain) {
		this.isdealedMain = isdealedMain;
	}

	public String getIsdealedWallFloor() {
		return isdealedWallFloor;
	}

	public void setIsdealedWallFloor(String isdealedWallFloor) {
		this.isdealedWallFloor = isdealedWallFloor;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	public String getOrderDesignerName() {
		return orderDesignerName;
	}

	public void setOrderDesignerName(String orderDesignerName) {
		this.orderDesignerName = orderDesignerName;
	}

	public String getOrderDesignerPhone() {
		return orderDesignerPhone;
	}

	public void setOrderDesignerPhone(String orderDesignerPhone) {
		this.orderDesignerPhone = orderDesignerPhone;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Integer getChangeBillCount() {
		return changeBillCount;
	}

	public void setChangeBillCount(Integer changeBillCount) {
		this.changeBillCount = changeBillCount;
	}

	public Double getWallFloorTileSquareBudget() {
		return wallFloorTileSquareBudget;
	}

	public void setWallFloorTileSquareBudget(Double wallFloorTileSquareBudget) {
		this.wallFloorTileSquareBudget = wallFloorTileSquareBudget;
	}

}