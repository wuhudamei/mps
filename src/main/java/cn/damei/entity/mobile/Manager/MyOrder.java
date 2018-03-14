package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class MyOrder extends  DataEntity2<MyOrder>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderNumber;		// 订单编号
	private String contractNumber;		// 合同编号
	private String customerType;		// 客户类型
	private String customerDescription;		// 客户属性描述
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
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
	private String contractTime;		// 合同工期
	private Date signContractDate;		// 签约日期
	private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
	private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
	private String orderInspector;		// 订单质检员
	private String itemManager;		// 项目经理
	private String storeId;		// 门店id
	private String cusManager;		// 客户经理
	private String cadPath;		// 图纸路径
	private String materialPath;		// 订单清单excel
	private String materialRemarks;		// excel 备注
	private Date beginContractStartDate;		// 开始 合同开工日期
	private Date endContractStartDate;		// 结束 合同开工日期
	private Date beginSignContractDate;		// 开始 签约日期
	private Date endSignContractDate;		// 结束 签约日期
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Integer itemManagerId;
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
	public String getContractTime() {
		return contractTime;
	}
	public void setContractTime(String contractTime) {
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCusManager() {
		return cusManager;
	}
	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}
	public String getCadPath() {
		return cadPath;
	}
	public void setCadPath(String cadPath) {
		this.cadPath = cadPath;
	}
	public String getMaterialPath() {
		return materialPath;
	}
	public void setMaterialPath(String materialPath) {
		this.materialPath = materialPath;
	}
	public String getMaterialRemarks() {
		return materialRemarks;
	}
	public void setMaterialRemarks(String materialRemarks) {
		this.materialRemarks = materialRemarks;
	}
	public Date getBeginContractStartDate() {
		return beginContractStartDate;
	}
	public void setBeginContractStartDate(Date beginContractStartDate) {
		this.beginContractStartDate = beginContractStartDate;
	}
	public Date getEndContractStartDate() {
		return endContractStartDate;
	}
	public void setEndContractStartDate(Date endContractStartDate) {
		this.endContractStartDate = endContractStartDate;
	}
	public Date getBeginSignContractDate() {
		return beginSignContractDate;
	}
	public void setBeginSignContractDate(Date beginSignContractDate) {
		this.beginSignContractDate = beginSignContractDate;
	}
	public Date getEndSignContractDate() {
		return endSignContractDate;
	}
	public void setEndSignContractDate(Date endSignContractDate) {
		this.endSignContractDate = endSignContractDate;
	}
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}
	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	public Date getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	
	
	
}
