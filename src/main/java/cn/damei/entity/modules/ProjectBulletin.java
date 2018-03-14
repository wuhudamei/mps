/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 订单管理Entity
 * @author llp
 * @version 2016-10-09
 */
public class ProjectBulletin extends DataEntity2<ProjectBulletin> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orderNumber;		// 订单编号
	private String contractNumber;		// 合同编号
	private String customerType;		// 客户类型
	private String customerAddress;		// 客户地址
	private Date actualStartDate;		// 实际开工日期
	private Date actualEndDate;		// 实际竣工日期
	private String customerDescription;		// 客户属性描述
	private String itemManagerId;		// 项目经理id
	private String signFlag;		// 是否签到(根据签到表)
	private String delayType;		// 延期类型
	private String orderInspectorId;		// order_inspector_id
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
	private String ordertaskpackStatus;		// ordertaskpack_status
	private String projectMode;//产业模式
	private String managerName;//项目经理名字
	/*
	 * 时间段
	 */
	//合同开工日期时间段
	private Date contractBegin;
	private Date contractEnd;
	
	//实际开工日期时间段
	private Date actualBegin;
	private Date actualEnd;
	
	
	//字段VO
	private String nodeName;//节点名称
	private Integer nodeIndex; 
	private Integer nodeId;
	private Integer nodePlanPicCount;
	private String isDone;
	private Date nodeRealDoneDate;
	private Integer doneDate; //开工天数
	
	public ProjectBulletin() {
		super();
	}

	public ProjectBulletin(Integer id){
		super(id);
	}

	@Length(min=1, max=100, message="订单编号长度必须介于 1 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Integer getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Integer doneDate) {
		this.doneDate = doneDate;
	}

	@Length(min=0, max=100, message="合同编号长度必须介于 0 和 100 之间")
	public String getContractNumber() {
		return contractNumber;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
	@Length(min=0, max=3, message="客户类型长度必须介于 0 和 3 之间")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	@Length(min=0, max=255, message="客户地址长度必须介于 0 和 255 之间")
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	
	@Length(min=0, max=64, message="客户属性描述长度必须介于 0 和 64 之间")
	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}
	
	@Length(min=0, max=100, message="项目经理id长度必须介于 0 和 100 之间")
	public String getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	
	@Length(min=0, max=10, message="是否签到(根据签到表)长度必须介于 0 和 10 之间")
	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
	
	@Length(min=0, max=10, message="延期类型长度必须介于 0 和 10 之间")
	public String getDelayType() {
		return delayType;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}
	
	@Length(min=0, max=11, message="order_inspector_id长度必须介于 0 和 11 之间")
	public String getOrderInspectorId() {
		return orderInspectorId;
	}

	public void setOrderInspectorId(String orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	
	@Length(min=1, max=16, message="客户姓名长度必须介于 1 和 16 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=1, max=11, message="客户电话长度必须介于 1 和 11 之间")
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Length(min=1, max=32, message="小区名称长度必须介于 1 和 32 之间")
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	@Length(min=1, max=16, message="几号楼长度必须介于 1 和 16 之间")
	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	@Length(min=1, max=16, message="几单元长度必须介于 1 和 16 之间")
	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}
	
	@Length(min=1, max=16, message="哪一室长度必须介于 1 和 16 之间")
	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}
	
	@Length(min=0, max=255, message="地图坐标长度必须介于 0 和 255 之间")
	public String getMapCoordinate() {
		return mapCoordinate;
	}

	public void setMapCoordinate(String mapCoordinate) {
		this.mapCoordinate = mapCoordinate;
	}
	
	@Length(min=0, max=32, message="套餐类型长度必须介于 0 和 32 之间")
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	@Length(min=0, max=32, message="片区长度必须介于 0 和 32 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=16, message="房屋类型长度必须介于 0 和 16 之间")
	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	
	@Length(min=0, max=16, message="户型长度必须介于 0 和 16 之间")
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
	@Length(min=0, max=2, message="新房老房  1为新房  0为老房  默认老房长度必须介于 0 和 2 之间")
	public String getHouseIsNew() {
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
	}
	
	@Length(min=0, max=2, message="是否有电梯  1代表有   0代表没有   默认没有长度必须介于 0 和 2 之间")
	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	
	@Length(min=0, max=12, message="设计师姓名长度必须介于 0 和 12 之间")
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	@Length(min=0, max=12, message="设计师电话长度必须介于 0 和 12 之间")
	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}
	
	@Length(min=0, max=12, message="跟单员姓名长度必须介于 0 和 12 之间")
	public String getOrderReporterName() {
		return orderReporterName;
	}

	public void setOrderReporterName(String orderReporterName) {
		this.orderReporterName = orderReporterName;
	}
	
	@Length(min=0, max=12, message="跟单员电话长度必须介于 0 和 12 之间")
	public String getOrderReporterPhone() {
		return orderReporterPhone;
	}

	public void setOrderReporterPhone(String orderReporterPhone) {
		this.orderReporterPhone = orderReporterPhone;
	}
	
	@Length(min=0, max=12, message="客服姓名长度必须介于 0 和 12 之间")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Length(min=0, max=12, message="客服电话长度必须介于 0 和 12 之间")
	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="合同开工日期不能为空")
	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	@Length(min=0, max=12, message="建筑面积长度必须介于 0 和 12 之间")
	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	
	@Length(min=0, max=12, message="合同面积长度必须介于 0 和 12 之间")
	public String getContractArea() {
		return contractArea;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	
	@Length(min=0, max=12, message="合同工期长度必须介于 0 和 12 之间")
	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}
	
	@Length(min=1, max=12, message="订单状态码  创建订单成功默认状态码 105长度必须介于 1 和 12 之间")
	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}
	
	@Length(min=1, max=64, message="订单状态码详情   默认详情 确认订单   状态码105长度必须介于 1 和 64 之间")
	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}
	
	@Length(min=0, max=64, message="订单质检员长度必须介于 0 和 64 之间")
	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	
	@Length(min=0, max=64, message="项目经理长度必须介于 0 和 64 之间")
	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	
	@Length(min=1, max=64, message="门店id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=64, message="客户经理长度必须介于 0 和 64 之间")
	public String getCusManager() {
		return cusManager;
	}

	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}
	
	@Length(min=0, max=1, message="ordertaskpack_status长度必须介于 0 和 1 之间")
	public String getOrdertaskpackStatus() {
		return ordertaskpackStatus;
	}

	public void setOrdertaskpackStatus(String ordertaskpackStatus) {
		this.ordertaskpackStatus = ordertaskpackStatus;
	}

	public Date getContractBegin() {
		return contractBegin;
	}

	public void setContractBegin(Date contractBegin) {
		this.contractBegin = contractBegin;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public Date getActualBegin() {
		return actualBegin;
	}

	public void setActualBegin(Date actualBegin) {
		this.actualBegin = actualBegin;
	}

	public Date getActualEnd() {
		return actualEnd;
	}

	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(Integer nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getNodePlanPicCount() {
		return nodePlanPicCount;
	}

	public void setNodePlanPicCount(Integer nodePlanPicCount) {
		this.nodePlanPicCount = nodePlanPicCount;
	}

	public String getIsDone() {
		return isDone;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}

	public Date getNodeRealDoneDate() {
		return nodeRealDoneDate;
	}

	public void setNodeRealDoneDate(Date nodeRealDoneDate) {
		this.nodeRealDoneDate = nodeRealDoneDate;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

}