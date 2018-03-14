package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.mobile.Worker.TaskPackagePicture;

public class BizOrderTaskpackage extends DataEntity2<BizOrderTaskpackage>{

	/**
	 * www 任务包明细所用的entity
	 */
	private static final long serialVersionUID = 1L;
	private Integer engineDepartId;
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	private Integer storeId;
	private Integer orderId;
	private String orderTaskpackageCode;//任务包标号 
	private String packageName;
	private Date planStartdate;
	private Date planEnddate;
	private Date actualStartdate;
	private Date actualEnddate;
	private String packageStateId;
	private String packageStateName;
	private Integer groupId;
	private String groupRealname;
	private String itemCustomer;
	private Integer itemManagerId;
	private String customerName; 
	private String customerMessage;
	private String orderNumber; //订单编号 订单表
	private String communityName;//小区名称 订单表
	private Date approveSalaryTime;//工人同意薪酬时间 结算明细表
	private Date acceptanceTime; //任务包验收时间 --结算单checkdate
	//private Date approveCheckTime; //结算员通过时间 暂缺
//	private Double total; //任务包的总价
	private Double laborAuxiliaryMaterialsBudgetAmount;
	private String managerPhone;
	private String groupPhone;
	private Integer days; //延迟的天数  已完工的天数
	private Integer count; //催促验收的次数
	private Date dispatchTime; //任务包派工时间
	private String inspectorPhone; //质检员手机号 订单中取
	private String inspectorName; //质检员  订单中取 
	private Date recheckTime;  //复检时间  暂缺 recheck_datetime
	private Date beginRecheckTime;
	private Date endRecheckTime;
	private Integer settlementId;
	private String projectMode;//工程模式
	//查询的时间条件字段
	private Date beginCreatedate; //任务包创建时间
	private Date endCreatedate;
	private Date beginPlanStartdate;
	private Date endPlanStartdate;
	private Date beginPlanEnddate;
	private Date endPlanEnddate;
	private Date beginActualStartdate;
	private Date endActualStartdate;
	private Date beginActualEnddate;
	private Date endActualEnddate;
	private Date beginApproveSalaryTime;
	private Date endApproveSalaryTime;
	private Date beginAcceptanceTime;
	private Date endAcceptanceTime;
	private List<String> statusList = new ArrayList<String>();
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>(); 
	private String leaflet;//派单人
	private Date leafletDate;//派单日期
	private Date beginleafletdate;//开始派工时间
	private Date endleafletdate;//结束派工时间
	private String isScrap; // 是否作废
	
	
	
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public Date getBeginleafletdate() {
		return beginleafletdate;
	}
	public void setBeginleafletdate(Date beginleafletdate) {
		this.beginleafletdate = beginleafletdate;
	}
	public Date getEndleafletdate() {
		return endleafletdate;
	}
	public void setEndleafletdate(Date endleafletdate) {
		this.endleafletdate = endleafletdate;
	}
	public String getLeaflet() {
		return leaflet;
	}
	public void setLeaflet(String leaflet) {
		this.leaflet = leaflet;
	}
	public Date getLeafletDate() {
		return leafletDate;
	}
	public void setLeafletDate(Date leafletDate) {
		this.leafletDate = leafletDate;
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
	private String isZero; //任务包总金额是否为零 1不是0
	private List<TaskPackagePicture> pictures = null;
	
	public List<TaskPackagePicture> getPictures() {
		return pictures;
	}
	public void setPictures(List<TaskPackagePicture> pictures) {
		this.pictures = pictures;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
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
	public String getOrderTaskpackageCode() {
		return orderTaskpackageCode;
	}
	public void setOrderTaskpackageCode(String orderTaskpackageCode) {
		this.orderTaskpackageCode = orderTaskpackageCode;
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
	public String getPackageStateId() {
		return packageStateId;
	}
	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
	}
	public String getPackageStateName() {
		return packageStateName;
	}
	public void setPackageStateName(String packageStateName) {
		this.packageStateName = packageStateName;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupRealname() {
		return groupRealname;
	}
	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}
	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public Date getApproveSalaryTime() {
		return approveSalaryTime;
	}
	public void setApproveSalaryTime(Date approveSalaryTime) {
		this.approveSalaryTime = approveSalaryTime;
	}
	public Date getAcceptanceTime() {
		return acceptanceTime;
	}
	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	public Date getBeginPlanStartdate() {
		return beginPlanStartdate;
	}
	public void setBeginPlanStartdate(Date beginPlanStartdate) {
		this.beginPlanStartdate = beginPlanStartdate;
	}
	public Date getEndPlanStartdate() {
		return endPlanStartdate;
	}
	public void setEndPlanStartdate(Date endPlanStartdate) {
		this.endPlanStartdate = endPlanStartdate;
	}
	public Date getBeginPlanEnddate() {
		return beginPlanEnddate;
	}
	public void setBeginPlanEnddate(Date beginPlanEnddate) {
		this.beginPlanEnddate = beginPlanEnddate;
	}
	public Date getEndPlanEnddate() {
		return endPlanEnddate;
	}
	public void setEndPlanEnddate(Date endPlanEnddate) {
		this.endPlanEnddate = endPlanEnddate;
	}
	public Date getBeginActualStartdate() {
		return beginActualStartdate;
	}
	public void setBeginActualStartdate(Date beginActualStartdate) {
		this.beginActualStartdate = beginActualStartdate;
	}
	public Date getEndActualStartdate() {
		return endActualStartdate;
	}
	public void setEndActualStartdate(Date endActualStartdate) {
		this.endActualStartdate = endActualStartdate;
	}
	public Date getBeginActualEnddate() {
		return beginActualEnddate;
	}
	public void setBeginActualEnddate(Date beginActualEnddate) {
		this.beginActualEnddate = beginActualEnddate;
	}
	public Date getEndActualEnddate() {
		return endActualEnddate;
	}
	public void setEndActualEnddate(Date endActualEnddate) {
		this.endActualEnddate = endActualEnddate;
	}
	public Date getBeginApproveSalaryTime() {
		return beginApproveSalaryTime;
	}
	public void setBeginApproveSalaryTime(Date beginApproveSalaryTime) {
		this.beginApproveSalaryTime = beginApproveSalaryTime;
	}
	public Date getEndApproveSalaryTime() {
		return endApproveSalaryTime;
	}
	public void setEndApproveSalaryTime(Date endApproveSalaryTime) {
		this.endApproveSalaryTime = endApproveSalaryTime;
	}
	public Date getBeginAcceptanceTime() {
		return beginAcceptanceTime;
	}
	public void setBeginAcceptanceTime(Date beginAcceptanceTime) {
		this.beginAcceptanceTime = beginAcceptanceTime;
	}
	public Date getEndAcceptanceTime() {
		return endAcceptanceTime;
	}
	public void setEndAcceptanceTime(Date endAcceptanceTime) {
		this.endAcceptanceTime = endAcceptanceTime;
	}
	public Date getBeginCreatedate() {
		return beginCreatedate;
	}
	public void setBeginCreatedate(Date beginCreatedate) {
		this.beginCreatedate = beginCreatedate;
	}
	public Date getEndCreatedate() {
		return endCreatedate;
	}
	public void setEndCreatedate(Date endCreatedate) {
		this.endCreatedate = endCreatedate;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getGroupPhone() {
		return groupPhone;
	}
	public void setGroupPhone(String groupPhone) {
		this.groupPhone = groupPhone;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getDispatchTime() {
		return dispatchTime;
	}
	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	public String getInspectorPhone() {
		return inspectorPhone;
	}
	public void setInspectorPhone(String inspectorPhone) {
		this.inspectorPhone = inspectorPhone;
	}
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	public Date getRecheckTime() {
		return recheckTime;
	}
	public void setRecheckTime(Date recheckTime) {
		this.recheckTime = recheckTime;
	}
	public Date getBeginRecheckTime() {
		return beginRecheckTime;
	}
	public void setBeginRecheckTime(Date beginRecheckTime) {
		this.beginRecheckTime = beginRecheckTime;
	}
	public Date getEndRecheckTime() {
		return endRecheckTime;
	}
	public void setEndRecheckTime(Date endRecheckTime) {
		this.endRecheckTime = endRecheckTime;
	}
	public Integer getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}
	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}
	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}
	public String getIsZero() {
		return isZero;
	}
	public void setIsZero(String isZero) {
		this.isZero = isZero;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}
