
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class InstallPlanQuery extends DataEntity<InstallPlanQuery> {

	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer storeId;
	private String projectMode;
	private Date generateInstallPlanTime;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private Integer ManagerId;
	private String managerName;
	private Date contractStartDate;
	private Date actualStartDate;
	private Integer installCount;
	private Date startDate;
	private Date endDate;
	private Integer installOrder;
	private String installName;
	private Date planIntoDate;
	public Date getPlanIntoDate() {
		return planIntoDate;
	}
	public void setPlanIntoDate(Date planIntoDate) {
		this.planIntoDate = planIntoDate;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Date getGenerateInstallPlanTime() {
		return generateInstallPlanTime;
	}
	public void setGenerateInstallPlanTime(Date generateInstallPlanTime) {
		this.generateInstallPlanTime = generateInstallPlanTime;
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
	public Integer getManagerId() {
		return ManagerId;
	}
	public void setManagerId(Integer managerId) {
		ManagerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Integer getInstallCount() {
		return installCount;
	}
	public void setInstallCount(Integer installCount) {
		this.installCount = installCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getInstallOrder() {
		return installOrder;
	}
	public void setInstallOrder(Integer installOrder) {
		this.installOrder = installOrder;
	}
	public String getInstallName() {
		return installName;
	}
	public void setInstallName(String installName) {
		this.installName = installName;
	}
}