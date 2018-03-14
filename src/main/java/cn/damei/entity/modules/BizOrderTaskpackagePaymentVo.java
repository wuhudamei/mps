/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单VoEntity
 * @author qww
 * @version 2016-10-26
 */
public class BizOrderTaskpackagePaymentVo extends DataEntity2<BizOrderTaskpackagePaymentVo> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String orderTaskpackagePaymentCode;
	private String settlementNo;
	private String customerMessage;
	private String customerName;
	private String realName;
	private String groupRealname;
	private String packageName;
	private String status;
	private String statusName;
	private String frozenRemarksTow;//冻结/解冻说明
	private String orderTaskpackagePaymentType;
	private Double amount;
	
	private Date startDate;
	private Date endDate;
	private Integer orderTaskpackageSettlementId;
	private String packageCode;
	private Integer orderTaskpackageId;
	private Float advancePaymentRates;
	private Integer qcBillId; //质检单id

	private Date generatedDatetime;
	private Date beginGeneratedDatetime; // 付款单生成日期开始
	private Date endGeneratedDatetime; // 付款单生成日期结束
	private String itemCustomer; // 项目经理
	private List<String> paymentStatus;
	private String customerPhone; // 客户手机号
	private Integer enginDepartId; // 区域
	private String enginDepartName; // 区域名称
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	public BizOrderTaskpackagePaymentVo() {
		super();
	}

	public BizOrderTaskpackagePaymentVo(Integer id){
		super(id);
	}

	
	
	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

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

	public String getOrderTaskpackagePaymentCode() {
		return orderTaskpackagePaymentCode;
	}

	public void setOrderTaskpackagePaymentCode(String orderTaskpackagePaymentCode) {
		this.orderTaskpackagePaymentCode = orderTaskpackagePaymentCode;
	}

	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOrderTaskpackagePaymentType() {
		return orderTaskpackagePaymentType;
	}

	public void setOrderTaskpackagePaymentType(String orderTaskpackagePaymentType) {
		this.orderTaskpackagePaymentType = orderTaskpackagePaymentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Integer getOrderTaskpackageSettlementId() {
		return orderTaskpackageSettlementId;
	}

	public void setOrderTaskpackageSettlementId(Integer orderTaskpackageSettlementId) {
		this.orderTaskpackageSettlementId = orderTaskpackageSettlementId;
	}

	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}

	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}

	public Float getAdvancePaymentRates() {
		return advancePaymentRates;
	}

	public void setAdvancePaymentRates(Float advancePaymentRates) {
		this.advancePaymentRates = advancePaymentRates;
	}

	public Integer getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}

	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}

	public Date getBeginGeneratedDatetime() {
		return beginGeneratedDatetime;
	}

	public void setBeginGeneratedDatetime(Date beginGeneratedDatetime) {
		this.beginGeneratedDatetime = beginGeneratedDatetime;
	}

	public Date getEndGeneratedDatetime() {
		return endGeneratedDatetime;
	}

	public void setEndGeneratedDatetime(Date endGeneratedDatetime) {
		this.endGeneratedDatetime = endGeneratedDatetime;
	}

	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}

	public List<String> getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(List<String> paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public String getGroupRealname() {
		return groupRealname;
	}

	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}


	public String getFrozenRemarksTow() {
		return frozenRemarksTow;
	}

	public void setFrozenRemarksTow(String frozenRemarksTow) {
		this.frozenRemarksTow = frozenRemarksTow;
	}
}