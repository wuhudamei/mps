package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderInstallItemProblemVo extends DataEntity2<BizOrderInstallItemProblemVo>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private String orderNumber;
	private Integer storeId;
	private String storeName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String customerPhone;
	private String itemManager;
	private String itemManagerPhone;
	private Date contractStartDate;
	private Date contractEndDate;
	private String designerName;
	private String designerPhone;
	private String orderInspector;
	private String inspectorPhone;
	private Integer engineDepartId;
	private String engineDepartName;
	private Integer problemId;
	private Integer businessOnlyMarkInt;
	private Integer problemTypeId;
	private String isDelay;
	private Double delayDays;
	private String problemDescribe;
	private String status;
	private String statusName;
	private String businessType;
	
	private String installItemName;
	private Integer picturesCount;
	private String pictureType;
	private String problemTypeName;
	private String projectMode;
	private String projectModeName;
	private String logStatus;
	private String problemSolveRole;
	private String projectNote;
	private String materialNote;
	
	private Date beginCreateDate;
	private Date endCreateDate;
	private Date beginProjectCreateDate;
	private Date endProjectCreateDate;
	private Date beginMaterialCreateDate;
	private Date endMaterialCreateDate;
	private String problemSolveNotes;
	private List<String> paramStatus;
	private Date projectCreateDate;
	private Date materialCreateDate;
	
	private Integer materialApplyEmployeeId;
	private String materialApplyEmployeeName;
	private String materialApplyEmployeePhone;
	private String materialCreateBy;
	private Integer problemApplyEmployeeId;
	private String problemApplyEmployeeName;
	private String problemApplyEmployeePhone;
	
	
	private Integer supplierId;
	private String supplierName;
	private Integer projectInstallItemId;
	private String isOn;
	private Integer projectInstallItemIdStop;
	private	List<Integer> projectInstallItemIdList = null;
	

	private String officeId;
	private String userId;
	private String parentId;
	private String name;
	
	private List<String> phones;
	
	
	
	public String getPictureType() {
		return pictureType;
	}
	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}
	public Integer getMaterialApplyEmployeeId() {
		return materialApplyEmployeeId;
	}
	public void setMaterialApplyEmployeeId(Integer materialApplyEmployeeId) {
		this.materialApplyEmployeeId = materialApplyEmployeeId;
	}
	public String getMaterialApplyEmployeeName() {
		return materialApplyEmployeeName;
	}
	public void setMaterialApplyEmployeeName(String materialApplyEmployeeName) {
		this.materialApplyEmployeeName = materialApplyEmployeeName;
	}
	public String getMaterialApplyEmployeePhone() {
		return materialApplyEmployeePhone;
	}
	public void setMaterialApplyEmployeePhone(String materialApplyEmployeePhone) {
		this.materialApplyEmployeePhone = materialApplyEmployeePhone;
	}
	public String getMaterialCreateBy() {
		return materialCreateBy;
	}
	public void setMaterialCreateBy(String materialCreateBy) {
		this.materialCreateBy = materialCreateBy;
	}
	public Integer getProblemApplyEmployeeId() {
		return problemApplyEmployeeId;
	}
	public void setProblemApplyEmployeeId(Integer problemApplyEmployeeId) {
		this.problemApplyEmployeeId = problemApplyEmployeeId;
	}
	public String getProblemApplyEmployeeName() {
		return problemApplyEmployeeName;
	}
	public void setProblemApplyEmployeeName(String problemApplyEmployeeName) {
		this.problemApplyEmployeeName = problemApplyEmployeeName;
	}
	public String getProblemApplyEmployeePhone() {
		return problemApplyEmployeePhone;
	}
	public void setProblemApplyEmployeePhone(String problemApplyEmployeePhone) {
		this.problemApplyEmployeePhone = problemApplyEmployeePhone;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
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
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	
	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}
	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}
	public Integer getProblemTypeId() {
		return problemTypeId;
	}
	public void setProblemTypeId(Integer problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	public String getIsDelay() {
		return isDelay;
	}
	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	public Double getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(Double delayDays) {
		this.delayDays = delayDays;
	}
	public String getProblemDescribe() {
		return problemDescribe;
	}
	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInstallItemName() {
		return installItemName;
	}
	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}
	public Integer getPicturesCount() {
		return picturesCount;
	}
	public void setPicturesCount(Integer picturesCount) {
		this.picturesCount = picturesCount;
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
	public String getProblemTypeName() {
		return problemTypeName;
	}
	public void setProblemTypeName(String problemTypeName) {
		this.problemTypeName = problemTypeName;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getProblemSolveNotes() {
		return problemSolveNotes;
	}
	public void setProblemSolveNotes(String problemSolveNotes) {
		this.problemSolveNotes = problemSolveNotes;
	}
	public Date getBeginProjectCreateDate() {
		return beginProjectCreateDate;
	}
	public void setBeginProjectCreateDate(Date beginProjectCreateDate) {
		this.beginProjectCreateDate = beginProjectCreateDate;
	}
	public Date getEndProjectCreateDate() {
		return endProjectCreateDate;
	}
	public void setEndProjectCreateDate(Date endProjectCreateDate) {
		this.endProjectCreateDate = endProjectCreateDate;
	}
	public Date getBeginMaterialCreateDate() {
		return beginMaterialCreateDate;
	}
	public void setBeginMaterialCreateDate(Date beginMaterialCreateDate) {
		this.beginMaterialCreateDate = beginMaterialCreateDate;
	}
	public Date getEndMaterialCreateDate() {
		return endMaterialCreateDate;
	}
	public void setEndMaterialCreateDate(Date endMaterialCreateDate) {
		this.endMaterialCreateDate = endMaterialCreateDate;
	}
	public List<String> getParamStatus() {
		return paramStatus;
	}
	public void setParamStatus(List<String> paramStatus) {
		this.paramStatus = paramStatus;
	}
	public Date getProjectCreateDate() {
		return projectCreateDate;
	}
	public void setProjectCreateDate(Date projectCreateDate) {
		this.projectCreateDate = projectCreateDate;
	}
	public Date getMaterialCreateDate() {
		return materialCreateDate;
	}
	public void setMaterialCreateDate(Date materialCreateDate) {
		this.materialCreateDate = materialCreateDate;
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
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public String getInspectorPhone() {
		return inspectorPhone;
	}
	public void setInspectorPhone(String inspectorPhone) {
		this.inspectorPhone = inspectorPhone;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public String getProjectNote() {
		return projectNote;
	}
	public void setProjectNote(String projectNote) {
		this.projectNote = projectNote;
	}
	public String getMaterialNote() {
		return materialNote;
	}
	public void setMaterialNote(String materialNote) {
		this.materialNote = materialNote;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public Integer getProblemId() {
		return problemId;
	}
	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	public String getProjectModeName() {
		return projectModeName;
	}
	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}
	public String getProblemSolveRole() {
		return problemSolveRole;
	}
	public void setProblemSolveRole(String problemSolveRole) {
		this.problemSolveRole = problemSolveRole;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getProjectInstallItemId() {
		return projectInstallItemId;
	}
	public void setProjectInstallItemId(Integer projectInstallItemId) {
		this.projectInstallItemId = projectInstallItemId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getIsOn() {
		return isOn;
	}
	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}
	public Integer getProjectInstallItemIdStop() {
		return projectInstallItemIdStop;
	}
	public void setProjectInstallItemIdStop(Integer projectInstallItemIdStop) {
		this.projectInstallItemIdStop = projectInstallItemIdStop;
	}
	public List<Integer> getProjectInstallItemIdList() {
		return projectInstallItemIdList;
	}
	public void setProjectInstallItemIdList(List<Integer> projectInstallItemIdList) {
		this.projectInstallItemIdList = projectInstallItemIdList;
	}
	
	
}
