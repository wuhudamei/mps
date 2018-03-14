package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class InspectPunishMoneyQueryEntity  extends DataEntity<InspectPunishMoneyQueryEntity>{


	private static final long serialVersionUID = 1L;
	private Integer checkItemId;
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	private String checkNodeName;
	public String getCheckNodeName() {
		return checkNodeName;
	}
	public void setCheckNodeName(String checkNodeName) {
		this.checkNodeName = checkNodeName;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getLou() {
		return lou;
	}
	public void setLou(String lou) {
		this.lou = lou;
	}
	public String getDanyuan() {
		return danyuan;
	}
	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getCheckTypeName() {
		return checkTypeName;
	}
	public void setCheckTypeName(String checkTypeName) {
		this.checkTypeName = checkTypeName;
	}
	public String getCheckItemName() {
		return checkItemName;
	}
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}
	public String getOrderInspectorName() {
		return orderInspectorName;
	}
	public void setOrderInspectorName(String orderInspectorName) {
		this.orderInspectorName = orderInspectorName;
	}
	public String getCheckManName() {
		return checkManName;
	}
	public void setCheckManName(String checkManName) {
		this.checkManName = checkManName;
	}
	public Integer getInspectId() {
		return inspectId;
	}
	public void setInspectId(Integer inspectId) {
		this.inspectId = inspectId;
	}
	public Date getApplyReportDate() {
		return applyReportDate;
	}
	public void setApplyReportDate(Date applyReportDate) {
		this.applyReportDate = applyReportDate;
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
	public String getIllegalName() {
		return illegalName;
	}
	public void setIllegalName(String illegalName) {
		this.illegalName = illegalName;
	}
	public Double getPunishMoney() {
		return punishMoney;
	}
	public void setPunishMoney(Double punishMoney) {
		this.punishMoney = punishMoney;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private Integer storeId;
	private String projectMode;
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	private String storeName;
	private String xiaoqu;
	private String lou;
	private String danyuan;
	private String shi;
	private String customerName;
	private String reportCode;
	private String reportType;
	private String checkTypeName;
	private String checkItemName;
	private  String orderInspectorName;
	private String checkManName;
	private Integer inspectId;
	private Date applyReportDate;
	private Date startDate;
	private Date endDate;
	private  String illegalName;
	private Double  punishMoney;
	private String    remarks;
	private String orderNumber;
	private Integer managerId;
	private String managerName;
	private Integer  billId;


	private String orderManagerName;
	private String orderPqcName;

	public String getOrderManagerName() {
		return orderManagerName;
	}

	public void setOrderManagerName(String orderManagerName) {
		this.orderManagerName = orderManagerName;
	}

	public String getOrderPqcName() {
		return orderPqcName;
	}

	public void setOrderPqcName(String orderPqcName) {
		this.orderPqcName = orderPqcName;
	}

	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	private  String workerGroupLeaderName;
	
	private Integer engineDepartId;
	private String engineDepartName;
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
	private Double managerScore;
	private Double inspectorScore;
	private Double inspectorMoney;
	private Double workerScore;
	private Double workerMoney;
	
	public String getWorkerGroupLeaderName() {
		return workerGroupLeaderName;
	}
	public void setWorkerGroupLeaderName(String workerGroupLeaderName) {
		this.workerGroupLeaderName = workerGroupLeaderName;
	}
	
	public Double getManagerScore() {
		return managerScore;
	}
	public void setManagerScore(Double managerScore) {
		this.managerScore = managerScore;
	}
	public Double getInspectorScore() {
		return inspectorScore;
	}
	public void setInspectorScore(Double inspectorScore) {
		this.inspectorScore = inspectorScore;
	}
	public Double getInspectorMoney() {
		return inspectorMoney;
	}
	public void setInspectorMoney(Double inspectorMoney) {
		this.inspectorMoney = inspectorMoney;
	}
	public Double getWorkerScore() {
		return workerScore;
	}
	public void setWorkerScore(Double workerScore) {
		this.workerScore = workerScore;
	}
	public Double getWorkerMoney() {
		return workerMoney;
	}
	public void setWorkerMoney(Double workerMoney) {
		this.workerMoney = workerMoney;
	}
	
	
	
	
}
