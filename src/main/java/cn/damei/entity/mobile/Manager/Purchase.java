package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Purchase extends DataEntity2<Purchase>{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer purchaseId;
	private String purchaseCode;
	private String purchaseType;
	private String receiverName;
	private String receiverPhone;
	private Date applyReceiveTime;
	private Integer applyEmployee;
	private Date applyTime;
	private String status;
	private String statusName;
	private Double totalPrice;
	
	private Date transferSupplierDatetime;
	private Date receiveAllGoodsDatetime;
	private Double overNumber;
	private String overReasonType;
	private String overReasonWords;
	private Integer supplierId;
	private String supplierName;
	private String supplierPhone;
	private String supplierContactsName;
	private String isElevator;
	private Double upstairsPay;
	private Integer upstairsFloor;
	private String statusDescribe;
	private Double purchaseCountTotal;
	
	private Integer count;
	private Integer applyFiveMinuteCount;
	private Integer purchaseCount;
	private String businessType;
	private String businessViewerOnlyMark;
	private Integer businessViewerEmployeeId;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public Date getApplyReceiveTime() {
		return applyReceiveTime;
	}
	public void setApplyReceiveTime(Date applyReceiveTime) {
		this.applyReceiveTime = applyReceiveTime;
	}
	public Integer getApplyEmployee() {
		return applyEmployee;
	}
	public void setApplyEmployee(Integer applyEmployee) {
		this.applyEmployee = applyEmployee;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessViewerOnlyMark() {
		return businessViewerOnlyMark;
	}
	public void setBusinessViewerOnlyMark(String businessViewerOnlyMark) {
		this.businessViewerOnlyMark = businessViewerOnlyMark;
	}
	public Integer getBusinessViewerEmployeeId() {
		return businessViewerEmployeeId;
	}
	public void setBusinessViewerEmployeeId(Integer businessViewerEmployeeId) {
		this.businessViewerEmployeeId = businessViewerEmployeeId;
	}
	public Date getTransferSupplierDatetime() {
		return transferSupplierDatetime;
	}
	public void setTransferSupplierDatetime(Date transferSupplierDatetime) {
		this.transferSupplierDatetime = transferSupplierDatetime;
	}
	public Date getReceiveAllGoodsDatetime() {
		return receiveAllGoodsDatetime;
	}
	public void setReceiveAllGoodsDatetime(Date receiveAllGoodsDatetime) {
		this.receiveAllGoodsDatetime = receiveAllGoodsDatetime;
	}
	public Double getOverNumber() {
		return overNumber;
	}
	public void setOverNumber(Double overNumber) {
		this.overNumber = overNumber;
	}
	public String getOverReasonType() {
		return overReasonType;
	}
	public void setOverReasonType(String overReasonType) {
		this.overReasonType = overReasonType;
	}
	public String getOverReasonWords() {
		return overReasonWords;
	}
	public void setOverReasonWords(String overReasonWords) {
		this.overReasonWords = overReasonWords;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getIsElevator() {
		return isElevator;
	}
	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	public Double getUpstairsPay() {
		return upstairsPay;
	}
	public void setUpstairsPay(Double upstairsPay) {
		this.upstairsPay = upstairsPay;
	}
	public Integer getUpstairsFloor() {
		return upstairsFloor;
	}
	public void setUpstairsFloor(Integer upstairsFloor) {
		this.upstairsFloor = upstairsFloor;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	public String getSupplierContactsName() {
		return supplierContactsName;
	}
	public void setSupplierContactsName(String supplierContactsName) {
		this.supplierContactsName = supplierContactsName;
	}
	public Integer getApplyFiveMinuteCount() {
		return applyFiveMinuteCount;
	}
	public void setApplyFiveMinuteCount(Integer applyFiveMinuteCount) {
		this.applyFiveMinuteCount = applyFiveMinuteCount;
	}
	public String getStatusDescribe() {
		return statusDescribe;
	}
	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	public Double getPurchaseCountTotal() {
		return purchaseCountTotal;
	}
	public void setPurchaseCountTotal(Double purchaseCountTotal) {
		this.purchaseCountTotal = purchaseCountTotal;
	}
	
	
	
	
	
}