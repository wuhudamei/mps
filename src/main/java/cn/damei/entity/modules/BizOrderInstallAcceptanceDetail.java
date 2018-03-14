package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 *	@author llp
 *	说明： 安装验收明细
 *	@version 20161124-20161204
 */
@SuppressWarnings("serial")
public class BizOrderInstallAcceptanceDetail extends DataEntity2<BizOrderInstallAcceptanceDetail> {
	private Integer id;// 主键
	private String orderNumber;// 订单编号
	private String contractNumber;// 合同编号
	private String customerType;// 客户类型
	private String customerDescription;// 客户属性描述
	private String customerName;// 客户姓名
	private String customerPhone;// 客户电话
	private String customerAddress;// 客户地址
	private String communityName;// 小区名称
	private String buildNumber;// 几号楼
	private String buildUnit;// 几单元
	private String buildRoom;// 哪一室
	private String mapCoordinate;// 地图坐标
	private String saleType;// 套餐类型
	private String area;// 片区
	private String buildType;// 房屋类型
	private String houseType;// 户型
	private String houseIsNew;// 新房老房 1为新房 0为老房 默认老房
	private String isElevator;// 是否有电梯 1代表有 0代表没有 默认没有
	private String designerName;// 设计师姓名
	private String designerPhone;// 设计师电话
	private String orderReporterName;// 跟单员姓名
	private String orderReporterPhone;// 跟单员电话
	private String serviceName;// 客服姓名
	private String servicePhone;// 客服电话
	private Date contractStartDate;// 合同开工日期
	private Date contractEndDate;// 合同竣工日期
	private String coveredArea;// 建筑面积
	private String contractArea;// 合同面积 小数两位
	private Integer contractTime;// 合同工期
	private Date signContractDate;// 签约日期
	private String orderStatusNumber;// 订单状态码 创建订单成功默认状态码 105
	private String orderStatusDescription;// 订单状态码详情 默认详情 确认订单 状态码105
	private String orderInspector;// 订单质检员
	private Date createDate;// 创建时间
	private Date updateDate;// 更新时间
	private String remarks;// 备注
	private String delFlag;// 删除状态 1:删除 0:不删除 默认不删除
	private String itemManager;// 项目经理
	private Integer storeId;// 门店ID
	private String cusManager;// 客户经理
	private String orderTaskPackStatus;// 任务包生成状态
	private Date actualStartDate;// 实际开工日期
	private Date actualEndDate;// 实际竣工日期
	private Integer itemManagerId;// 项目经理id
	private String signFlag;// 是否签到(根据签到表)
	private String delayType;// 延期类型
	private Integer orderInspectorId;// 质检id
	private String projectMode;//产业模式
	
	//jsp页面取值需要字段
	private String employeeRealName;//员工姓名
	private String employeePhone;//员工电话
	
	/**
	 * 日期区间段查询
	 */
	private Date installRealIntoDateStart;//实际进场日期
	private Date installRealIntoDateEnd;//实际进场日期
	private Date installRealCompleteDateEnd;//实际完工日期
	private Date installRealCompleteDateStart;//实际完工日期
	private Date confirmAcceptanceDateBegin;//提交验收日期
	private Date confirmAcceptanceDateEnd;//提交验收日期
	
	private Date installApplyIntoDate;//期望进场日期
	private String installStatus;//安装项状态
	private String installItemName;//安装项名称
	private Date installApplyInfoCreateDate;//申请日期
	private Integer installID;//安装项主键
	private Date installUpdateDate;//安装项修改时间（提交验收时间）
	private Date installRealIntoDate;//实际进场时间
	private Date installRealCompleteDate;//实际完工时间
	private String installIsCompleteDelay;//是否完工延期 -- '0.否；1.是
	private String installDeplayRemarks;//完工延期描述
	
	private List<String> phones;
	
	
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	//权限控制
	private String officeId; //
	private String userId; //用户id
	private String parentId; //父id
	private String name; //门店的名称
	
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}

	public String getDelayType() {
		return delayType;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}

	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}

	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}

	public String getEmployeeRealName() {
		return employeeRealName;
	}

	public void setEmployeeRealName(String employeeRealName) {
		this.employeeRealName = employeeRealName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public Date getInstallApplyIntoDate() {
		return installApplyIntoDate;
	}

	public void setInstallApplyIntoDate(Date installApplyIntoDate) {
		this.installApplyIntoDate = installApplyIntoDate;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public void setInstallStatus(String installStatus) {
		this.installStatus = installStatus;
	}

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public Date getInstallApplyInfoCreateDate() {
		return installApplyInfoCreateDate;
	}

	public void setInstallApplyInfoCreateDate(Date installApplyInfoCreateDate) {
		this.installApplyInfoCreateDate = installApplyInfoCreateDate;
	}

	public Integer getInstallID() {
		return installID;
	}

	public void setInstallID(Integer installID) {
		this.installID = installID;
	}

	public Date getInstallRealCompleteDateEnd() {
		return installRealCompleteDateEnd;
	}

	public void setInstallRealCompleteDateEnd(Date installRealCompleteDateEnd) {
		this.installRealCompleteDateEnd = installRealCompleteDateEnd;
	}

	public Date getInstallRealCompleteDateStart() {
		return installRealCompleteDateStart;
	}

	public void setInstallRealCompleteDateStart(Date installRealCompleteDateStart) {
		this.installRealCompleteDateStart = installRealCompleteDateStart;
	}

	public Date getInstallRealIntoDateStart() {
		return installRealIntoDateStart;
	}

	public void setInstallRealIntoDateStart(Date installRealIntoDateStart) {
		this.installRealIntoDateStart = installRealIntoDateStart;
	}

	public Date getInstallRealIntoDateEnd() {
		return installRealIntoDateEnd;
	}

	public void setInstallRealIntoDateEnd(Date installRealIntoDateEnd) {
		this.installRealIntoDateEnd = installRealIntoDateEnd;
	}

	public Date getInstallRealIntoDate() {
		return installRealIntoDate;
	}

	public void setInstallRealIntoDate(Date installRealIntoDate) {
		this.installRealIntoDate = installRealIntoDate;
	}

	public Date getInstallRealCompleteDate() {
		return installRealCompleteDate;
	}

	public void setInstallRealCompleteDate(Date installRealCompleteDate) {
		this.installRealCompleteDate = installRealCompleteDate;
	}

	public Date getConfirmAcceptanceDateBegin() {
		return confirmAcceptanceDateBegin;
	}

	public void setConfirmAcceptanceDateBegin(Date confirmAcceptanceDateBegin) {
		this.confirmAcceptanceDateBegin = confirmAcceptanceDateBegin;
	}

	public Date getConfirmAcceptanceDateEnd() {
		return confirmAcceptanceDateEnd;
	}

	public void setConfirmAcceptanceDateEnd(Date confirmAcceptanceDateEnd) {
		this.confirmAcceptanceDateEnd = confirmAcceptanceDateEnd;
	}

	public Date getInstallUpdateDate() {
		return installUpdateDate;
	}

	public void setInstallUpdateDate(Date installUpdateDate) {
		this.installUpdateDate = installUpdateDate;
	}

	public String getInstallIsCompleteDelay() {
		return installIsCompleteDelay;
	}

	public void setInstallIsCompleteDelay(String installIsCompleteDelay) {
		this.installIsCompleteDelay = installIsCompleteDelay;
	}

	public String getInstallDeplayRemarks() {
		return installDeplayRemarks;
	}

	public void setInstallDeplayRemarks(String installDeplayRemarks) {
		this.installDeplayRemarks = installDeplayRemarks;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

}
