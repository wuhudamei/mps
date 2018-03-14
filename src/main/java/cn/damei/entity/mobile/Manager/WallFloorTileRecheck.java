package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 墙地砖实测面积复核
 * 
 * @author Administrator
 *
 */
public class WallFloorTileRecheck extends DataEntity2<WallFloorTileRecheck> {

	private static final long serialVersionUID = 1L;

	private Integer orderId; // 订单id
	private String orderNumber; // 订单编号
	private String itemManager; // 项目经理姓名
	private String customerName; // 客户姓名
	private String customerPhone; // 客户电话

	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室

	private String isScrap; // 订单是否作废 1为是 0为否
	private String orderStatusNumber; // 订单状态码 创建订单成功默认状态码 105
	private String orderStatusDescription; // 订单状态码详情

	private Date actualStartDate; // 实际开工日期
	private String actualStartDateString; // 实际开工日期 字符串类型

	private String purchaseType; // 采购单类型

	private Integer wallFloorTileRecheckId; // 墙地砖复尺表id
	private Double squareBudget; // 预算面积
	private Double squareQuota; // 定额面积
	private Double squarePurchase; // 实际下单面积
	private Double squareMeasure; // 实测面积
	private Date planMeasureDate; // 计划测量日期
	private Date realMeasureDate; // 实际测量日期
	private String realMeasureDateString; // 实际测量日期 字符串类型
	private String measureRemarks; // 实测说明
	private Double price; // 墙地砖单价
	private Double assessSquareError1; // 考核面积误差1
	private Double assessSquareError2; // 考核面积误差2
	private Double assessAmount1; // 考核金额1
	private Double assessAmount2; // 考核金额2
	private String assessPersonName1; // 被考核人姓名1
	private String assessPersonName2; // 被考核人姓名2
	private String status; // 状态
	private String statusDescribe; // 状态描述
	private Date statusDatetime; // 状态日期
	private Integer statusOperateEmployeeId; // 状态操作人员ID
	private String recheckRemarks; // 复尺备注
	private String flag; // 不同意复尺的标志 0为 不同意

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