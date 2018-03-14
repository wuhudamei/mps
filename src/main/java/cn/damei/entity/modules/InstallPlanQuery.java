/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 安装项计划查询Entity
 * 
 * @author 梅浩
 * @version 2017-02-06
 */
public class InstallPlanQuery extends DataEntity<InstallPlanQuery> {

	private static final long serialVersionUID = 1L;
	private Integer orderId;//订单id
	private Integer storeId;//门店id
	private String projectMode;//工程模式
	private Date generateInstallPlanTime;//生成安装计划时间
	private String communityName;//小区名称
	private String buildNumber;//楼号
	private String buildUnit;//单元号
	private String buildRoom;//室
	private String customerName;//客户名称
	private Integer ManagerId;//项目经理id
	private String managerName;//经理名称
	private Date contractStartDate;//合同开工时间
	private Date actualStartDate;//实际开工时间
	private Integer installCount;//安装项数量
	private Date startDate;//查询: 生成安装项日期 开始
	private Date endDate;//查询:  生成安装项日期  结束
	private Integer installOrder;//安装项顺序
	private String installName;//安装项名称
	private Date planIntoDate;//计划安装日期
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