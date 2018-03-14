package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class QualityControlFineUpdateEntity  extends DataEntity<QualityControlFineUpdateEntity>{


	private static final long serialVersionUID = 1L;
	private Integer checkItemId;
	
	private Integer storeId;
	private String projectMode;
	private String storeName;
	private String xiaoqu;
	private String lou;
	private String danyuan;
	private String shi;
	private String customerName;
	private String customerPhone;
	private String reportCode;
	private String reportType;
	private String checkTypeName;
	private String checkItemName;
	private  String orderInspectorName;
	private String checkManName;
	private Integer inspectId;
	private Date applyReportDate;
	private  String illegalName;
	private String    remarks;
	private String orderNumber;
	private Integer managerId;
	private String managerName;
	private Integer  billId;

	private  String workerGroupLeaderName;
	private Integer engineDepartId;
	private String engineDepartName;
	private String checkNodeName;
	private Integer managerScore;
	private Double  punishMoney;
	private Integer inspectorScore;
	private Double inspectorMoney;
	private Integer workerScore;
	private Double workerMoney;
	
	private Integer managerScoreOld;
	private Double  punishMoneyOld;
	private Integer inspectorScoreOld;
	private Double inspectorMoneyOld;
	private Integer workerScoreOld;
	private Double workerMoneyOld;
	private Integer qcCheckItemId;
	private Integer orderId;
	private String packageName;
	private String packageStateId;
	private String packageStateName;
	private Date createDate;
	private Date modifyDatetime;
	private Date beginModifyDatetime;
	private Date endModifyDatetime;
	private Integer picCount;
	private String operatorName;
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getPicCount() {
		return picCount;
	}
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	public Integer getManagerScoreOld() {
		return managerScoreOld;
	}
	public void setManagerScoreOld(Integer managerScoreOld) {
		this.managerScoreOld = managerScoreOld;
	}
	public Double getPunishMoneyOld() {
		return punishMoneyOld;
	}
	public void setPunishMoneyOld(Double punishMoneyOld) {
		this.punishMoneyOld = punishMoneyOld;
	}
	public Integer getInspectorScoreOld() {
		return inspectorScoreOld;
	}
	public void setInspectorScoreOld(Integer inspectorScoreOld) {
		this.inspectorScoreOld = inspectorScoreOld;
	}
	public Double getInspectorMoneyOld() {
		return inspectorMoneyOld;
	}
	public void setInspectorMoneyOld(Double inspectorMoneyOld) {
		this.inspectorMoneyOld = inspectorMoneyOld;
	}
	public Integer getWorkerScoreOld() {
		return workerScoreOld;
	}
	public void setWorkerScoreOld(Integer workerScoreOld) {
		this.workerScoreOld = workerScoreOld;
	}
	public Double getWorkerMoneyOld() {
		return workerMoneyOld;
	}
	public void setWorkerMoneyOld(Double workerMoneyOld) {
		this.workerMoneyOld = workerMoneyOld;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getBeginModifyDatetime() {
		return beginModifyDatetime;
	}
	public void setBeginModifyDatetime(Date beginModifyDatetime) {
		this.beginModifyDatetime = beginModifyDatetime;
	}
	public Date getEndModifyDatetime() {
		return endModifyDatetime;
	}
	public void setEndModifyDatetime(Date endModifyDatetime) {
		this.endModifyDatetime = endModifyDatetime;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQcCheckItemId() {
		return qcCheckItemId;
	}
	public void setQcCheckItemId(Integer qcCheckItemId) {
		this.qcCheckItemId = qcCheckItemId;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
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
	
	public String getWorkerGroupLeaderName() {
		return workerGroupLeaderName;
	}
	public void setWorkerGroupLeaderName(String workerGroupLeaderName) {
		this.workerGroupLeaderName = workerGroupLeaderName;
	}
	
	
	public Double getInspectorMoney() {
		return inspectorMoney;
	}
	public void setInspectorMoney(Double inspectorMoney) {
		this.inspectorMoney = inspectorMoney;
	}
	
	public Double getWorkerMoney() {
		return workerMoney;
	}
	public void setWorkerMoney(Double workerMoney) {
		this.workerMoney = workerMoney;
	}
	public Integer getManagerScore() {
		return managerScore;
	}
	public void setManagerScore(Integer managerScore) {
		this.managerScore = managerScore;
	}
	public Integer getInspectorScore() {
		return inspectorScore;
	}
	public void setInspectorScore(Integer inspectorScore) {
		this.inspectorScore = inspectorScore;
	}
	public Integer getWorkerScore() {
		return workerScore;
	}
	public void setWorkerScore(Integer workerScore) {
		this.workerScore = workerScore;
	}
	
	
	
	
}
