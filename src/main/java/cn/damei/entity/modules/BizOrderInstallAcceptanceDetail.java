package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class BizOrderInstallAcceptanceDetail extends DataEntity2<BizOrderInstallAcceptanceDetail> {
	private Integer id;
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
	private Date createDate;
	private Date updateDate;
	private String remarks;
	private String delFlag;
	private String itemManager;
	private Integer storeId;
	private String cusManager;
	private String orderTaskPackStatus;
	private Date actualStartDate;
	private Date actualEndDate;
	private Integer itemManagerId;
	private String signFlag;
	private String delayType;
	private Integer orderInspectorId;
	private String projectMode;
	

	private String employeeRealName;
	private String employeePhone;
	

	private Date installRealIntoDateStart;
	private Date installRealIntoDateEnd;
	private Date installRealCompleteDateEnd;
	private Date installRealCompleteDateStart;
	private Date confirmAcceptanceDateBegin;
	private Date confirmAcceptanceDateEnd;
	
	private Date installApplyIntoDate;
	private String installStatus;
	private String installItemName;
	private Date installApplyInfoCreateDate;
	private Integer installID;
	private Date installUpdateDate;
	private Date installRealIntoDate;
	private Date installRealCompleteDate;
	private String installIsCompleteDelay;
	private String installDeplayRemarks;
	
	private List<String> phones;
	
	
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}


	private String officeId;
	private String userId;
	private String parentId;
	private String name;
	
	
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
