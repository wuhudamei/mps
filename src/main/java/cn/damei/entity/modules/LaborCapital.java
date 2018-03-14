package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class LaborCapital extends DataEntity2<LaborCapital> {

	private static final long serialVersionUID = 1L;

	private String storeId;

	private String projectMode;

	private Integer enginDepartId;

	private String departmentName;

	private String custumer;

	private String customerAddress;

	private String orderStatusNumber;

	private String itemManager;

	private String taskPackageType;

	private String packageStateId;

	private String groupLeaderPhone;
	

	private String inspectorName;

	private Date getOrderDatetime;

	private String packageName;

	private String groupLeaderName;

	private Date actualStartdate;

	private Date actualEnddate;

	private Double punishAmerce;

	private Double delayAmerce;

	private Double companyDeductAmount;

	private String orderStatusDescription;

	private String packageStatename;
	
	public String getPackageStatename() {
		return packageStatename;
	}
	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
	}
	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}
	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
	public String getCustumer() {
		return custumer;
	}
	public void setCustumer(String custumer) {
		this.custumer = custumer;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}
	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getTaskPackageType() {
		return taskPackageType;
	}
	public void setTaskPackageType(String taskPackageType) {
		this.taskPackageType = taskPackageType;
	}
	public String getPackageStateId() {
		return packageStateId;
	}
	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
	}
	public String getGroupLeaderPhone() {
		return groupLeaderPhone;
	}
	public void setGroupLeaderPhone(String groupLeaderPhone) {
		this.groupLeaderPhone = groupLeaderPhone;
	}
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}
	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getGroupLeaderName() {
		return groupLeaderName;
	}
	public void setGroupLeaderName(String groupLeaderName) {
		this.groupLeaderName = groupLeaderName;
	}
	public Date getActualStartdate() {
		return actualStartdate;
	}
	public void setActualStartdate(Date actualStartdate) {
		this.actualStartdate = actualStartdate;
	}
	public Date getActualEnddate() {
		return actualEnddate;
	}
	public void setActualEnddate(Date actualEnddate) {
		this.actualEnddate = actualEnddate;
	}
	public Double getPunishAmerce() {
		return punishAmerce;
	}
	public void setPunishAmerce(Double punishAmerce) {
		this.punishAmerce = punishAmerce;
	}
	public Double getDelayAmerce() {
		return delayAmerce;
	}
	public void setDelayAmerce(Double delayAmerce) {
		this.delayAmerce = delayAmerce;
	}
	public Double getCompanyDeductAmount() {
		return companyDeductAmount;
	}
	public void setCompanyDeductAmount(Double companyDeductAmount) {
		this.companyDeductAmount = companyDeductAmount;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}