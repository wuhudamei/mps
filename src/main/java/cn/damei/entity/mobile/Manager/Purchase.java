package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Purchase extends DataEntity2<Purchase>{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;		//订单id
	private Integer purchaseId;	//采购单ID
	private String purchaseCode;	//采购单编码
	private String purchaseType;	//采购单类型
	private String receiverName;	//收货人
	private String receiverPhone;	//收货电话
	private Date applyReceiveTime;	//期望送货日期
	private Integer applyEmployee;	//申请人
	private Date applyTime;			//申请时间
	private String status;			//状态id 
	private String statusName;			//状态
	private Double totalPrice;     //总价格
	
	private Date transferSupplierDatetime;	//转给供应商日期时间
	private Date receiveAllGoodsDatetime;	//收货完成日期时间
	private Double overNumber;	//超出多少
	private String overReasonType;	//超出类型
	private String overReasonWords;	//超出原因说明
	private Integer supplierId;	//供应商ID
	private String supplierName;	//供应商名称
	private String supplierPhone;	//供应商电话
	private String supplierContactsName;//供应商联系人
	private String isElevator;	//是否有电梯
	private Double upstairsPay;	//上楼费
	private Integer upstairsFloor;	//几楼
	private String statusDescribe;			//状态描述
	private Double purchaseCountTotal;
	
	private Integer count; //是否是已读信息
	private Integer applyFiveMinuteCount; //5分钟内申请墙地砖的数量
	private Integer purchaseCount; //是否存在了对应非作废的采购单
	private String businessType;	//日志表类型
	private String businessViewerOnlyMark;//日志表唯一标识
	private Integer businessViewerEmployeeId;//日志表的操作人
	
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