package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class WorkTaskPackage extends DataEntity2<WorkTaskPackage>{

	/**
	 * @author 汪文文
	 * @version 2016-9-20
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer storeId;		// 门店Id
	private int picId;          //任务包图片id
	private Integer orderId;		// 订单Id
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String packageCode;		// 任务包编号
	private String packageName;		// 任务包名称
	private Date planStartdate;		// 计划开工日期
	private Date planEnddate;		// 计划完工日期
	private Date actualStartdate;		// 实际开工日期
	private Date actualEnddate;		// 实际完工日期
	private String packageStateid;		// 任务包状态Id
	private String packageStatename;		// 任务包状态名称
	private String empGroupid;		// 工人组ID
	private Integer groupId;		// 组长ID
	//private String groupRealname;		// 组长真实姓名
	private String itemCustomer;		// 项目经理
	private String settleStyle; // 结算方式，1-包工包料，2-包工
	private Double laborBudgetAmount;// 人工费预算总金额
	private Double auxiliaryMaterialsBudgetAmount;// 辅料费预算总金额
	
	private String isScrap;//是否作废
	
	
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
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
	//private String taskPackageType;		// 任务包类型
	private String customerName;		// 客户姓名
//	private String dispatcher;			//调度员
	private String customerMessage;     //客户信息
	//private Date dispatchTime;        //派工时间
	//private String isOvertime;    //是否超时
	private String leaderPhone ;//手机
	private String leaderName;//组长姓名
	private String managerPhone;//项目经理手机
	private Integer managerId;
	private Integer taskPackageTemplatId;//任务包模板id
	private String customerPhone; //客户手机号
	private String workerName;
	private String workerPhone;
	
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerPhone() {
		return workerPhone;
	}
	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public Integer getManagerId() {
		return managerId;
	}


	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}


	public String getManagerPhone() {
		return managerPhone;
	}


	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}


	public int getPicId() {
		return picId;
	}


	public void setPicId(int picId) {
		this.picId = picId;
	}


	public String getLeaderName() {
		return leaderName;
	}


	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	private Double laborAuxiliaryMaterialsBudgetAmount;//任务包工序总价


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


	public WorkTaskPackage() {
		super();
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}


	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Date getPlanStartdate() {
		return planStartdate;
	}
	public void setPlanStartdate(Date planStartdate) {
		this.planStartdate = planStartdate;
	}
	public Date getPlanEnddate() {
		return planEnddate;
	}
	public void setPlanEnddate(Date planEnddate) {
		this.planEnddate = planEnddate;
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
	public String getPackageStateid() {
		return packageStateid;
	}
	public void setPackageStateid(String packageStateid) {
		this.packageStateid = packageStateid;
	}
	public String getPackageStatename() {
		return packageStatename;
	}
	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
	}
	public String getEmpGroupid() {
		return empGroupid;
	}
	public void setEmpGroupid(String empGroupid) {
		this.empGroupid = empGroupid;
	}
	
	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
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
	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}
	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}
	public String getSettleStyle() {
		return settleStyle;
	}
	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
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
}
