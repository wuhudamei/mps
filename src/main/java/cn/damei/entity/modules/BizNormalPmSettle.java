
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class BizNormalPmSettle extends DataEntity<BizNormalPmSettle> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String storeName;

	private Integer orderId;
	private String settleRemarks;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	private Integer applyEmployeeId;
	private Date applyDatetime;
	private Integer checkEmployeeId;
	private String settleStatus;
private String settleStatusName;
	private Date statusDatetime;
	private String checkReply;
	private Double settleAmount;
	private Integer projectMode;
	private String projectModeName;

	private String orderNumber;
	private Integer engineDepartId;
	private String engineDepartName;
	private String settleNodeName;
	private String settleNodeId;
	private String customerAddress;
	private String managerName;
	private String pqcName;
	private String customerName;
	private Date startApplyDate;
	private Date endApplyDate;
	private Integer settleId;


	public String getSettleStatusName() {
		return settleStatusName;
	}

	public void setSettleStatusName(String settleStatusName) {
		this.settleStatusName = settleStatusName;
	}

	public Integer getSettleId() {
		return settleId;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}

	public Integer getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}

	public String getSettleNodeName() {
		return settleNodeName;
	}

	public void setSettleNodeName(String settleNodeName) {
		this.settleNodeName = settleNodeName;
	}

	public String getSettleNodeId() {
		return settleNodeId;
	}

	public void setSettleNodeId(String settleNodeId) {
		this.settleNodeId = settleNodeId;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPqcName() {
		return pqcName;
	}

	public void setPqcName(String pqcName) {
		this.pqcName = pqcName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getStartApplyDate() {
		return startApplyDate;
	}

	public void setStartApplyDate(Date startApplyDate) {
		this.startApplyDate = startApplyDate;
	}

	public Date getEndApplyDate() {
		return endApplyDate;
	}

	public void setEndApplyDate(Date endApplyDate) {
		this.endApplyDate = endApplyDate;
	}

	public String getSettleRemarks() {
		return settleRemarks;
	}

	public void setSettleRemarks(String settleRemarks) {
		this.settleRemarks = settleRemarks;
	}

	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}

	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}

	public Date getApplyDatetime() {
		return applyDatetime;
	}

	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}

	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public String getCheckReply() {
		return checkReply;
	}

	public void setCheckReply(String checkReply) {
		this.checkReply = checkReply;
	}

	public Double getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}

	public BizNormalPmSettle() {
		super();
	}

	public BizNormalPmSettle(String id){
		super(id);
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
	

	
}