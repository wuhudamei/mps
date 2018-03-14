package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class SettlementForDetail extends DataEntity2<SettlementForDetail>{

	/**
	 * 结算单的明细查询entity
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer storeId; //门店
	private String projectMode;
	private String settlementNo; 
	private String status;
	private String orderTaskpackageCode; //任务包编号
	private Integer orderTaskpackageId; 
	private String packageName;
	private Integer groupId;
	private String groupRealname;
	private String orderNumber; //订单 表
	private String customerName; 
	private String customerMessage;
	private String itemCustomer;
	private Integer itemManagerId;
	private String communityName; //订单表
	private Double settlementAmount;//结算金额
	private Double budgetAmount; //预算金额
	private Date checkDate; //验收时间
	private Date approveSalaryTime; //工人同意薪酬时间
	
	private List<String> statusList = null; //状态
	private Date beginCheckDate;
	private Date endCheckDate;
	private Date beginApproveSalaryTime;
	private Date endApproveSalaryTime;
	private Date beginCreateDate;
	private Date endCreateDate;
	
	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderTaskpackageCode() {
		return orderTaskpackageCode;
	}
	public void setOrderTaskpackageCode(String orderTaskpackageCode) {
		this.orderTaskpackageCode = orderTaskpackageCode;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public Double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public Double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getApproveSalaryTime() {
		return approveSalaryTime;
	}
	public void setApproveSalaryTime(Date approveSalaryTime) {
		this.approveSalaryTime = approveSalaryTime;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public Date getBeginCheckDate() {
		return beginCheckDate;
	}
	public void setBeginCheckDate(Date beginCheckDate) {
		this.beginCheckDate = beginCheckDate;
	}
	public Date getEndCheckDate() {
		return endCheckDate;
	}
	public void setEndCheckDate(Date endCheckDate) {
		this.endCheckDate = endCheckDate;
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
