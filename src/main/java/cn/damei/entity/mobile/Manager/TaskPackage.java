package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月19日 下午4:05:22 项目经理下的订单中的所有任务包 实例
 */

public class TaskPackage extends DataEntity<TaskPackage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer picId;
	private Integer packageId; //任务包id
	private Integer orderId;//所属订单id
	private String packageCode;
	private String packageName;//任务包名称
	private String stateId;//状态id
	private String stateName;// 任务包状态名称
	private Date startTime;// 开工日期
	private Date endTime; // 完工日期
	private String customerName; // 顾客信息,  姓名
	private String customerMessage;//顾客信息, 地址
	private String orderBy;//排序
	private String leaderName; //相关的工长姓名
	private String leaderId; //组长id
	private String leaderPhone; //组长手机
	private Double laborAuxiliaryMaterialsBudgetAmount;//工料费预算总金额
	private Integer itemManagerId;//项目经理id
	private String orderTaskPackageCode;//订单任务包编号
	private Date actualStartdate;// 实际开工日期
	private Date actualEnddate; // 实际完工日期
	private String packageStateId; // 任务包状态Id
	private String packageStatename; // 任务包状态名称
	private Integer taskPackageTemplatId; // 任务包模板id
	private Date planStartDate;//计划开始时间
	private Date planEndDate;//计划结束时间
	private String empGroupid; // 工人组ID
	private Integer groupId;
	private String groupRealname;
	private String itemCustomer;
	private String taskPackageType;
	private String dispatcher;
	private Date dispatchTime;
	private String isOvertime;
	private String projectMode;
private String text;
	public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

	private Double settlementAmount; // 结算金额
	private Date checkDate; // 验收日期
	private Integer settlementId; // 结算单id
	private Integer inspectorId; // 质检员id
	private String inspectorPhone; // 质检员手机号
	private String managerName; // 项目经理名称
	private String managerPhone; // 项目经理手机号
	private String customerPhone; // 客户手机号
	private String groupPhone; // 工人组长手机号
	private String lon;
	private String lat;
	
	private Double laborBudgetAmount = 0.0;// 人工费预算总金额
	private Double auxiliaryMaterialsBudgetAmount = 0.0;//辅料费预算总金额
	private String settleStyle;// 结算方式

	public String getGroupPhone() {
		return groupPhone;
	}

	public void setGroupPhone(String groupPhone) {
		this.groupPhone = groupPhone;
	}

	public Integer getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(Integer inspectorId) {
		this.inspectorId = inspectorId;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getIsOvertime() {
		return isOvertime;
	}

	public void setIsOvertime(String isOvertime) {
		this.isOvertime = isOvertime;
	}

	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getTaskPackageType() {
		return taskPackageType;
	}

	public void setTaskPackageType(String taskPackageType) {
		this.taskPackageType = taskPackageType;
	}

	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}

	public String getGroupRealname() {
		return groupRealname;
	}

	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getEmpGroupid() {
		return empGroupid;
	}

	public void setEmpGroupid(String empGroupid) {
		this.empGroupid = empGroupid;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getOrderTaskPackageCode() {
		return orderTaskPackageCode;
	}

	public void setOrderTaskPackageCode(String orderTaskPackageCode) {
		this.orderTaskPackageCode = orderTaskPackageCode;
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

	public String getPackageStateId() {
		return packageStateId;
	}

	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
	}

	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	public String getPackageStatename() {
		return packageStatename;
	}

	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
	}

	public Double getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public String getInspectorPhone() {
		return inspectorPhone;
	}

	public void setInspectorPhone(String inspectorPhone) {
		this.inspectorPhone = inspectorPhone;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Double getLaborBudgetAmount() {
		return laborBudgetAmount;
	}

	public void setLaborBudgetAmount(Double laborBudgetAmount) {
		this.laborBudgetAmount = laborBudgetAmount;
	}

	public Double getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}

	public void setAuxiliaryMaterialsBudgetAmount(Double auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
	}

	public String getSettleStyle() {
		return settleStyle;
	}

	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
}
