package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizAuxiliaryMaterialsVerify extends DataEntity2<BizAuxiliaryMaterialsVerify> {

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String verifyCode;
	private Date generateDatetime;
	private Date startDate;
	private Date endDate;
	private Integer orderTaskpackageSettlementCount;
	private Double auxiliaryMaterialsSupplierPriceAmount;
	private Double auxiliaryMaterialsWangzhenPriceAmount;
	private Double auxiliaryMaterialsLaborPriceAmount;
	private Integer auxiliaryMaterialsCount;
	private String status;
	private Date statusDatetime;
	private String statusRemark;
	private Integer statusOperateEmployeeId;
	private String  empName;
	private String cancelReason;
	private String supplierId;
	private String supplierName;
	

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Date getGenerateDatetime() {
		return generateDatetime;
	}

	public void setGenerateDatetime(Date generateDatetime) {
		this.generateDatetime = generateDatetime;
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

	public Integer getOrderTaskpackageSettlementCount() {
		return orderTaskpackageSettlementCount;
	}

	public void setOrderTaskpackageSettlementCount(Integer orderTaskpackageSettlementCount) {
		this.orderTaskpackageSettlementCount = orderTaskpackageSettlementCount;
	}

	public Double getAuxiliaryMaterialsSupplierPriceAmount() {
		return auxiliaryMaterialsSupplierPriceAmount;
	}

	public void setAuxiliaryMaterialsSupplierPriceAmount(Double auxiliaryMaterialsSupplierPriceAmount) {
		this.auxiliaryMaterialsSupplierPriceAmount = auxiliaryMaterialsSupplierPriceAmount;
	}

	public Double getAuxiliaryMaterialsWangzhenPriceAmount() {
		return auxiliaryMaterialsWangzhenPriceAmount;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setAuxiliaryMaterialsWangzhenPriceAmount(Double auxiliaryMaterialsWangzhenPriceAmount) {
		this.auxiliaryMaterialsWangzhenPriceAmount = auxiliaryMaterialsWangzhenPriceAmount;
	}

	public Double getAuxiliaryMaterialsLaborPriceAmount() {
		return auxiliaryMaterialsLaborPriceAmount;
	}

	public void setAuxiliaryMaterialsLaborPriceAmount(Double auxiliaryMaterialsLaborPriceAmount) {
		this.auxiliaryMaterialsLaborPriceAmount = auxiliaryMaterialsLaborPriceAmount;
	}

	public Integer getAuxiliaryMaterialsCount() {
		return auxiliaryMaterialsCount;
	}

	public void setAuxiliaryMaterialsCount(Integer auxiliaryMaterialsCount) {
		this.auxiliaryMaterialsCount = auxiliaryMaterialsCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public Integer getStatusOperateEmployeeId() {
		return statusOperateEmployeeId;
	}

	public void setStatusOperateEmployeeId(Integer statusOperateEmployeeId) {
		this.statusOperateEmployeeId = statusOperateEmployeeId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}
