package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class QualityControl extends DataEntity2<QualityControl>{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;	//订单id
	private String orderNumber;		// 订单编号
	private String contractNumber;		// 合同编号
	private String customerType;		// 客户类型
	private String customerDescription;		// 客户属性描述
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	
	private String customerAddress;     //客户地址
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String info;		//顾客信息
	
	private String mapCoordinate;		// 地图坐标
	private String saleType;		// 套餐类型
	private String area;		// 片区
	private String buildType;		// 房屋类型
	private String houseType;		// 户型
	private String houseIsNew;		// 新房老房  1为新房  0为老房  默认老房
	private String isElevator;		// 是否有电梯  1代表有   0代表没有   默认没有
	
	private String designerName;		// 设计师姓名
	private String designerPhone;		// 设计师电话
	private String orderReporterName;		// 跟单员姓名
	private String orderReporterPhone;		// 跟单员电话
	private String serviceName;		// 客服姓名
	private String servicePhone;		// 客服电话
	
	private Date contractStartDate;		// 合同开工日期
	private Date contractEndDate;		// 合同竣工日期
	private String coveredArea;		// 建筑面积
	private String contractArea;		// 合同面积
	private Integer contractTime;		// 合同工期
	private Date signContractDate;		// 签约日期
	
	private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
	private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
	private String orderInspector;		// 订单质检员
	private String orderInspectorPhone; //质检员电话
	private String itemManager;		// 项目经理
	private String phone;		//项目经理电话
	private Integer itemManagerId;  	//项目经理id
	private Integer storeId;		// 门店id
	private String cusManager;		// 客户经理
	
	
	private String orderTaskPackStatus; //任务包状态
	private Date actualStartDate;	//实际开工日期
	private Date actualEndDate;		//实际竣工日期
	private String signFlag;		//是否签到
	private Integer orderInspectorId;	//质检id
	private String projectMode; //工程模式
	
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}
	
	



}
