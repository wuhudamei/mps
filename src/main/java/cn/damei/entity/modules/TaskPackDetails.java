/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包详情
 * @author 张康健
 * @version 2017-3-25
 */
public class TaskPackDetails extends DataEntity2<TaskPackDetails> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;//订单ID
	private Integer storeId;		// 门店id
	private String name;//区域
	private String orderNumber;//订单编号
	private String orderTaskPackageCode;//任务包编号
	private String packageName;//任务包名字
	private String customerMessage;//客户地址，开工地点
	private String packageStatename;//任务包状态
	private String customerName;//客户名字
	private String customerPhone;//客户手机
	private String itemManager;//项目经理
	private String phone;//项目经理手机
	private Date createDate;//创建时间
	private Date dispatchTime;//派工时间
	private Date planStartdate;//计划开工时间
	private Date planEnddate;//计划竣工时间
	private Date actualStartdate;//实际开工时间
	private Date actualEnddate;//实际骏工时间
	private String laborAuxiliaryMaterialsBudgetAmount;//工料费预算金额
	private String laborBudgetAmount;// 人工费预算金额
	private String auxiliaryMaterialsBudgetAmount;//辅料费预算金额
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderTaskPackageCode() {
		return orderTaskPackageCode;
	}
	public void setOrderTaskPackageCode(String orderTaskPackageCode) {
		this.orderTaskPackageCode = orderTaskPackageCode;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public String getPackageStatename() {
		return packageStatename;
	}
	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getDispatchTime() {
		return dispatchTime;
	}
	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
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
	public String getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}
	public void setLaborAuxiliaryMaterialsBudgetAmount(String laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}
	public TaskPackDetails() {
		super();
	}
	public String getLaborBudgetAmount() {
		return laborBudgetAmount;
	}
	public void setLaborBudgetAmount(String laborBudgetAmount) {
		this.laborBudgetAmount = laborBudgetAmount;
	}
	public String getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}
	public void setAuxiliaryMaterialsBudgetAmount(String auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
	}
}