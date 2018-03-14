package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class MaterialManagement extends DataEntity2<MaterialManagement> {

	private static final long serialVersionUID = 1L;

	private String orderNumber;
	private String contractNumber;
	private String customerType;
	private String customerDescription;
	private String customerName;
	private String customerPhone;

	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;

	private String mapCoordinate;
	private String saleType;
	private String area;
	private String buildType;
	private String houseType;
	private String houseIsNew;
	private String isElevator;

	private String designerName;
	private String designerPhone;
	private String orderReporterName;
	private String orderReporterPhone;
	private String serviceName;
	private String servicePhone;

	private Date contractStartDate;
	private Date contractEndDate;
	private String coveredArea;
	private String contractArea;
	private Integer contractTime;
	private Date signContractDate;

	private String orderStatusNumber;
	private String orderStatusDescription;
	private String orderInspector;
	private String itemManager;
	private String phone;
	private Integer itemManagerId;
	private Integer storeId;
	private String cusManager;
	private String text;

	private String orderTaskPackStatus;
	private Date actualStartDate;
	private Date actualEndDate;
	private String signFlag;
	private String projectMode;
	private Integer allCount;
	private Integer orderId;
	private Integer purchaseId;
	private String purchaseStatus;
	private String purchaseType;

	private String actualStartDateString;
	private String contractStartDateString;

	private Integer wallFloorTileOrderCountId;
	private Double squareBudget;
	private Double squarePurchaseTotal;
	private Double squareReturn;
	private Double squarePurchaseReal;
	private Double squareSettle;
	private Double squareMeasure;
	private Double squareActual;

	private Double squareBudgetOne;
	private Double squareBudgetOne1;

	private String isScrap;

	public String getActualStartDateString() {
		return actualStartDateString;
	}

	public void setActualStartDateString(String actualStartDateString) {
		this.actualStartDateString = actualStartDateString;
	}

	public String getContractStartDateString() {
		return contractStartDateString;
	}

	public void setContractStartDateString(String contractStartDateString) {
		this.contractStartDateString = contractStartDateString;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public Double getSquareActual() {
		return squareActual;
	}

	public void setSquareActual(Double squareActual) {
		this.squareActual = squareActual;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getText() {
		return text;
	}

	public Double getSquareBudgetOne1() {
		return squareBudgetOne1;
	}

	public void setSquareBudgetOne1(Double squareBudgetOne1) {
		this.squareBudgetOne1 = squareBudgetOne1;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
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

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getMapCoordinate() {
		return mapCoordinate;
	}

	public void setMapCoordinate(String mapCoordinate) {
		this.mapCoordinate = mapCoordinate;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHouseIsNew() {
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
	}

	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}

	public String getOrderReporterName() {
		return orderReporterName;
	}

	public void setOrderReporterName(String orderReporterName) {
		this.orderReporterName = orderReporterName;
	}

	public String getOrderReporterPhone() {
		return orderReporterPhone;
	}

	public void setOrderReporterPhone(String orderReporterPhone) {
		this.orderReporterPhone = orderReporterPhone;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}

	public String getContractArea() {
		return contractArea;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}

	public Integer getContractTime() {
		return contractTime;
	}

	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
	}

	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}

	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCusManager() {
		return cusManager;
	}

	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}

	public String getOrderTaskPackStatus() {
		return orderTaskPackStatus;
	}

	public void setOrderTaskPackStatus(String orderTaskPackStatus) {
		this.orderTaskPackStatus = orderTaskPackStatus;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Integer getWallFloorTileOrderCountId() {
		return wallFloorTileOrderCountId;
	}

	public void setWallFloorTileOrderCountId(Integer wallFloorTileOrderCountId) {
		this.wallFloorTileOrderCountId = wallFloorTileOrderCountId;
	}

	public Double getSquareBudget() {
		return squareBudget;
	}

	public void setSquareBudget(Double squareBudget) {
		this.squareBudget = squareBudget;
	}

	public Double getSquarePurchaseTotal() {
		return squarePurchaseTotal;
	}

	public void setSquarePurchaseTotal(Double squarePurchaseTotal) {
		this.squarePurchaseTotal = squarePurchaseTotal;
	}

	public Double getSquareReturn() {
		return squareReturn;
	}

	public void setSquareReturn(Double squareReturn) {
		this.squareReturn = squareReturn;
	}

	public Double getSquarePurchaseReal() {
		return squarePurchaseReal;
	}

	public void setSquarePurchaseReal(Double squarePurchaseReal) {
		this.squarePurchaseReal = squarePurchaseReal;
	}

	public Double getSquareSettle() {
		return squareSettle;
	}

	public void setSquareSettle(Double squareSettle) {
		this.squareSettle = squareSettle;
	}

	public Double getSquareMeasure() {
		return squareMeasure;
	}

	public void setSquareMeasure(Double squareMeasure) {
		this.squareMeasure = squareMeasure;
	}

	public Double getSquareBudgetOne() {
		return squareBudgetOne;
	}

	public void setSquareBudgetOne(Double squareBudgetOne) {
		this.squareBudgetOne = squareBudgetOne;
	}

}
