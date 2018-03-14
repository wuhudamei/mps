package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class BizCompletedAudit extends DataEntity2<BizCompletedAudit> {
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
	private String province;// 省
	private String city;// 市
	private String county;// 区县
	private String detail_address;// 详细地址
	private String bizOrderAcceptArea;// 接单区域
	private String projectMode;//产业模式
	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private List<String> status;
	private Integer orderFinishProjectBillId;
	/**
	 * 日期区间段查询
	 */
	private Date appStartDate;
	private Date appEndDate;
	private Date realFinishProjectStartDate;
	private Date realFinishProjectEndDate;
	
	// jsp页面取值需要字段
	private String employeeRealName;// 员工姓名
	private String employeePhone;// 员工电话
	private Date realFinishProjectDate;// 项目经理申请竣工日期
	private Date applyDatetime;// 申请时间
	private String countPic;// 图片总数
	private String countTaskpackage;// 任务包总数
	
	private String empRealName;//员工姓名
	private String empPhone;//员工手机号
	private Integer empId;//员工编号


	public Integer getOrderFinishProjectBillId() {
		return orderFinishProjectBillId;
	}

	public void setOrderFinishProjectBillId(Integer orderFinishProjectBillId) {
		this.orderFinishProjectBillId = orderFinishProjectBillId;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
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

	public String getEmpRealName() {
		return empRealName;
	}

	public void setEmpRealName(String empRealName) {
		this.empRealName = empRealName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getBizOrderAcceptArea() {
		return bizOrderAcceptArea;
	}

	public void setBizOrderAcceptArea(String bizOrderAcceptArea) {
		this.bizOrderAcceptArea = bizOrderAcceptArea;
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

	public Date getRealFinishProjectDate() {
		return realFinishProjectDate;
	}

	public void setRealFinishProjectDate(Date realFinishProjectDate) {
		this.realFinishProjectDate = realFinishProjectDate;
	}

	public Date getApplyDatetime() {
		return applyDatetime;
	}

	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	public String getCountPic() {
		return countPic;
	}

	public void setCountPic(String countPic) {
		this.countPic = countPic;
	}

	public String getCountTaskpackage() {
		return countTaskpackage;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public void setCountTaskpackage(String countTaskpackage) {
		this.countTaskpackage = countTaskpackage;
	}

	public Date getAppStartDate() {
		return appStartDate;
	}

	public void setAppStartDate(Date appStartDate) {
		this.appStartDate = appStartDate;
	}

	public Date getAppEndDate() {
		return appEndDate;
	}

	public void setAppEndDate(Date appEndDate) {
		this.appEndDate = appEndDate;
	}

	public Date getRealFinishProjectStartDate() {
		return realFinishProjectStartDate;
	}

	public void setRealFinishProjectStartDate(Date realFinishProjectStartDate) {
		this.realFinishProjectStartDate = realFinishProjectStartDate;
	}

	public Date getRealFinishProjectEndDate() {
		return realFinishProjectEndDate;
	}

	public void setRealFinishProjectEndDate(Date realFinishProjectEndDate) {
		this.realFinishProjectEndDate = realFinishProjectEndDate;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

}
