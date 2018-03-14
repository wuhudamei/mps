package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class WallFloorTileRecheck extends DataEntity2<WallFloorTileRecheck> {

	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private String orderNumber;
	private String itemManager;
	private String customerName;
	private String customerPhone;

	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;

	private String isScrap;
	private String orderStatusNumber;
	private String orderStatusDescription;

	private Date actualStartDate;
	private String actualStartDateString;

	private String purchaseType;

	private Integer wallFloorTileRecheckId;
	private Double squareBudget;
	private Double squareQuota;
	private Double squarePurchase;
	private Double squareMeasure;
	private Date planMeasureDate;
	private Date realMeasureDate;
	private String realMeasureDateString;
	private String measureRemarks;
	private Double price;
	private Double assessSquareError1;
	private Double assessSquareError2;
	private Double assessAmount1;
	private Double assessAmount2;
	private String assessPersonName1;
	private String assessPersonName2;
	private String status;
	private String statusDescribe;
	private Date statusDatetime;
	private Integer statusOperateEmployeeId;
	private String recheckRemarks;
	private String flag;

	public WallFloorTileRecheck() {
		super();
	}

	public WallFloorTileRecheck(Integer id) {
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public String getActualStartDateString() {
		return actualStartDateString;
	}

	public void setActualStartDateString(String actualStartDateString) {
		this.actualStartDateString = actualStartDateString;
	}

	public Double getSquareBudget() {
		return squareBudget;
	}

	public void setSquareBudget(Double squareBudget) {
		this.squareBudget = squareBudget;
	}

	public Double getSquareQuota() {
		return squareQuota;
	}

	public void setSquareQuota(Double squareQuota) {
		this.squareQuota = squareQuota;
	}

	public Double getSquarePurchase() {
		return squarePurchase;
	}

	public void setSquarePurchase(Double squarePurchase) {
		this.squarePurchase = squarePurchase;
	}

	public Double getSquareMeasure() {
		return squareMeasure;
	}

	public void setSquareMeasure(Double squareMeasure) {
		this.squareMeasure = squareMeasure;
	}

	public Date getPlanMeasureDate() {
		return planMeasureDate;
	}

	public void setPlanMeasureDate(Date planMeasureDate) {
		this.planMeasureDate = planMeasureDate;
	}

	public Date getRealMeasureDate() {
		return realMeasureDate;
	}

	public void setRealMeasureDate(Date realMeasureDate) {
		this.realMeasureDate = realMeasureDate;
	}

	public String getMeasureRemarks() {
		return measureRemarks;
	}

	public void setMeasureRemarks(String measureRemarks) {
		this.measureRemarks = measureRemarks;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAssessSquareError1() {
		return assessSquareError1;
	}

	public void setAssessSquareError1(Double assessSquareError1) {
		this.assessSquareError1 = assessSquareError1;
	}

	public Double getAssessSquareError2() {
		return assessSquareError2;
	}

	public void setAssessSquareError2(Double assessSquareError2) {
		this.assessSquareError2 = assessSquareError2;
	}

	public Double getAssessAmount1() {
		return assessAmount1;
	}

	public void setAssessAmount1(Double assessAmount1) {
		this.assessAmount1 = assessAmount1;
	}

	public Double getAssessAmount2() {
		return assessAmount2;
	}

	public void setAssessAmount2(Double assessAmount2) {
		this.assessAmount2 = assessAmount2;
	}

	public String getAssessPersonName1() {
		return assessPersonName1;
	}

	public void setAssessPersonName1(String assessPersonName1) {
		this.assessPersonName1 = assessPersonName1;
	}

	public String getAssessPersonName2() {
		return assessPersonName2;
	}

	public void setAssessPersonName2(String assessPersonName2) {
		this.assessPersonName2 = assessPersonName2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public Integer getStatusOperateEmployeeId() {
		return statusOperateEmployeeId;
	}

	public void setStatusOperateEmployeeId(Integer statusOperateEmployeeId) {
		this.statusOperateEmployeeId = statusOperateEmployeeId;
	}

	public String getRecheckRemarks() {
		return recheckRemarks;
	}

	public void setRecheckRemarks(String recheckRemarks) {
		this.recheckRemarks = recheckRemarks;
	}

	public Integer getWallFloorTileRecheckId() {
		return wallFloorTileRecheckId;
	}

	public void setWallFloorTileRecheckId(Integer wallFloorTileRecheckId) {
		this.wallFloorTileRecheckId = wallFloorTileRecheckId;
	}

	public String getRealMeasureDateString() {
		return realMeasureDateString;
	}

	public void setRealMeasureDateString(String realMeasureDateString) {
		this.realMeasureDateString = realMeasureDateString;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

}