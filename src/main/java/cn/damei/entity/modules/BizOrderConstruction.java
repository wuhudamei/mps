/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 施工中项目查询
 * biz_order
 * @author llp
 * @version 2016-11-26
 */
public class BizOrderConstruction extends DataEntity2<BizOrderConstruction> {
	
	private static final long serialVersionUID = 1L;
	
	private String orderNumber;		// 订单编号
	private String contractNumber;		// 合同编号
	private String customerType;		// 客户类型
	private String customerDescription;		// 客户属性描述
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String customerAddress;		// 客户电话
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
	private Integer contractTime;		// 合同工期
	private Date signContractDate;		// 签约日期
	private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
	private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
	private String orderInspector;		// 订单质检员
	private String itemManager;		// 项目经理
	private String storeId;		// 门店id
	private String cusManager;		// 客户经理
	private Date beginContractStartDate;		// 开始 合同开工日期
	private Date endContractStartDate;		// 结束 合同开工日期
	private Date beginSignContractDate;		// 开始 签约日期
	private Date endSignContractDate;		// 结束 签约日期
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String orderTaskPackStatus; //任务包状态
	private Date actualStartDate;//实际开工日期
	private Date actualEndDate;//实际竣工日期
	private String signFlag;//是否签到(根据签到表)
	private String delayType;//开工延期类型
	private String projectMode;//产业模式
	
	//jsp用到的字段
	private String nodeName;//节点名称
	private String managerRealName;//项目经理名字
	private String inspectorName;//质检名字
	private String nodeIndex;//进度节点序号
	
	/**日期区间段查询条件***/
	/**************实际开工区间段******************/
	private Date actualStartDateBegin;
	private Date actualStartDateEnd;
	private Date contractStartDateBegin;
	private Date contractStartDateEnd;
	
	public BizOrderConstruction() {
		super();
	}

	public String getOrderNumber() {
		return orderNumber;
	}
	
	public Date getActualStartDateBegin() {
		return actualStartDateBegin;
	}

	public void setActualStartDateBegin(Date actualStartDateBegin) {
		this.actualStartDateBegin = actualStartDateBegin;
	}

	public Date getActualStartDateEnd() {
		return actualStartDateEnd;
	}

	public void setActualStartDateEnd(Date actualStartDateEnd) {
		this.actualStartDateEnd = actualStartDateEnd;
	}

	public String getNodeName() {
		return nodeName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getDelayType() {
		return delayType;
	}

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

	public String getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(String nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public String getManagerRealName() {
		return managerRealName;
	}

	public void setManagerRealName(String managerRealName) {
		this.managerRealName = managerRealName;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}

	public Date getContractStartDateBegin() {
		return contractStartDateBegin;
	}

	public void setContractStartDateBegin(Date contractStartDateBegin) {
		this.contractStartDateBegin = contractStartDateBegin;
	}

	public Date getContractStartDateEnd() {
		return contractStartDateEnd;
	}

	public void setContractStartDateEnd(Date contractStartDateEnd) {
		this.contractStartDateEnd = contractStartDateEnd;
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
	
	
	
	public Date getSignContractDate() {
		return signContractDate;
	}

	public Integer getContractTime() {
		return contractTime;
	}

	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
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

	public String getOrderTaskPackStatus() {
		return orderTaskPackStatus;
	}

	public void setOrderTaskPackStatus(String orderTaskPackStatus) {
		this.orderTaskPackStatus = orderTaskPackStatus;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}

}