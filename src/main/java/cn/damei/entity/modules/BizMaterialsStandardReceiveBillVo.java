package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class BizMaterialsStandardReceiveBillVo extends DataEntity2<BizMaterialsStandardReceiveBillVo>{

	private static final long serialVersionUID = 1L;
	
	private Integer storeId;
	private Integer orderId;
	private String materialsStandardReceiveBillCode;
	private Date receiveDatetime;
	private Integer receiveEmployeeId;
	private String receiveEmployeeName;
	private String employeeNo;
	private String orderNo;
	private String customerName;
	private Double detailAmount;
	private Double receiveBillAmount;
	private String isSettled;
	private Date beginReceiveDatetime;
	private Date endReceiveDatetime;
	
	private List<BizMaterialsStandardReceiveDetail> details;
	private Integer materialId;
	private String materialType;
	private String materialName;
	private Double materialPrice;
	
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public List<BizMaterialsStandardReceiveDetail> getDetails() {
		return details;
	}
	public void setDetails(List<BizMaterialsStandardReceiveDetail> details) {
		this.details = details;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Double getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
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
	public String getMaterialsStandardReceiveBillCode() {
		return materialsStandardReceiveBillCode;
	}
	public void setMaterialsStandardReceiveBillCode(String materialsStandardReceiveBillCode) {
		this.materialsStandardReceiveBillCode = materialsStandardReceiveBillCode;
	}
	public Date getReceiveDatetime() {
		return receiveDatetime;
	}
	public void setReceiveDatetime(Date receiveDatetime) {
		this.receiveDatetime = receiveDatetime;
	}
	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}
	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
	}
	public String getReceiveEmployeeName() {
		return receiveEmployeeName;
	}
	public void setReceiveEmployeeName(String receiveEmployeeName) {
		this.receiveEmployeeName = receiveEmployeeName;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getDetailAmount() {
		return detailAmount;
	}
	public void setDetailAmount(Double detailAmount) {
		this.detailAmount = detailAmount;
	}
	public Double getReceiveBillAmount() {
		return receiveBillAmount;
	}
	public void setReceiveBillAmount(Double receiveBillAmount) {
		this.receiveBillAmount = receiveBillAmount;
	}
	public String getIsSettled() {
		return isSettled;
	}
	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}
	public Date getBeginReceiveDatetime() {
		return beginReceiveDatetime;
	}
	public void setBeginReceiveDatetime(Date beginReceiveDatetime) {
		this.beginReceiveDatetime = beginReceiveDatetime;
	}
	public Date getEndReceiveDatetime() {
		return endReceiveDatetime;
	}
	public void setEndReceiveDatetime(Date endReceiveDatetime) {
		this.endReceiveDatetime = endReceiveDatetime;
	}
}
