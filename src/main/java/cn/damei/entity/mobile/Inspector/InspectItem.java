package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class InspectItem extends DataEntity2<InspectItem> {

	
	
	private Integer inspectBillId;
	private Integer itemId;
	private Integer orderId;
	private Integer  managerId;
	private Integer  responsibilityPersonM;
	private Integer  responsibilityPersonW;
	private Integer  taskPackageTemplatId;
	private String  managerName;
	private String  projectMode;
	private Integer  pqcId;

	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	private Integer  relatedBillCheckItemId;
	
	private String isRequired;
	private Integer checkItemId;
	private String checkItemName;
	private String isChoosed;

	private String isOk;
	private Double preScores;
	private Double actualScores;
	private String  isWarn;
	
	private String  isLocatedChange;
	private String  isLimitDateChange;
	private Date limitDate;
	private String limitChangeWay;
	private String  isPunishMoney;
	private Double  prePunishMoney;
	private Double  actualPunishMoney;
	private InspectKind   checkKind;
	

	private  Integer packId;
	private  Integer  workerGroupId;
	private  String workerGroupLeaderName;
	private String packName;
	private  Integer inspectorId;
	
	private Integer settleEmployeeId;
	private Double managerScore;
	private Double inspectorScore;
	private Double inspectorMoney;
	private Double workerScore;
	private Double workerMoney;

	public Integer getSettleEmployeeId() {
		return settleEmployeeId;
	}

	public void setSettleEmployeeId(Integer settleEmployeeId) {
		this.settleEmployeeId = settleEmployeeId;
	}

	public Integer getPqcId() {
		return pqcId;
	}

	public void setPqcId(Integer pqcId) {
		this.pqcId = pqcId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	private Double scores;

	public Double getScores() {
		return scores;
	}

	public void setScores(Double scores) {
		this.scores = scores;
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
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	public Integer getWorkerGroupId() {
		return workerGroupId;
	}
	public void setWorkerGroupId(Integer workerGroupId) {
		this.workerGroupId = workerGroupId;
	}
	public String getWorkerGroupLeaderName() {
		return workerGroupLeaderName;
	}
	public void setWorkerGroupLeaderName(String workerGroupLeaderName) {
		this.workerGroupLeaderName = workerGroupLeaderName;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public Integer getInspectorId() {
		return inspectorId;
	}
	public void setInspectorId(Integer inspectorId) {
		this.inspectorId = inspectorId;
	}
	public Integer getRelatedBillCheckItemId() {
		return relatedBillCheckItemId;
	}
	public void setRelatedBillCheckItemId(Integer relatedBillCheckItemId) {
		this.relatedBillCheckItemId = relatedBillCheckItemId;
	}
	public String getIsWarn() {
		return isWarn;
	}
	public void setIsWarn(String isWarn) {
		this.isWarn = isWarn;
	}
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public Double getPreScores() {
		return preScores;
	}
	public void setPreScores(Double preScores) {
		this.preScores = preScores;
	}
	public Double getActualScores() {
		return actualScores;
	}
	public void setActualScores(Double actualScores) {
		this.actualScores = actualScores;
	}
	public String getIsLocatedChange() {
		return isLocatedChange;
	}
	public void setIsLocatedChange(String isLocatedChange) {
		this.isLocatedChange = isLocatedChange;
	}
	public String getIsLimitDateChange() {
		return isLimitDateChange;
	}
	public void setIsLimitDateChange(String isLimitDateChange) {
		this.isLimitDateChange = isLimitDateChange;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getLimitChangeWay() {
		return limitChangeWay;
	}
	public void setLimitChangeWay(String limitChangeWay) {
		this.limitChangeWay = limitChangeWay;
	}
	public String getIsPunishMoney() {
		return isPunishMoney;
	}
	public void setIsPunishMoney(String isPunishMoney) {
		this.isPunishMoney = isPunishMoney;
	}
	public Double getPrePunishMoney() {
		return prePunishMoney;
	}
	public void setPrePunishMoney(Double prePunishMoney) {
		this.prePunishMoney = prePunishMoney;
	}
	public Double getActualPunishMoney() {
		return actualPunishMoney;
	}
	public void setActualPunishMoney(Double actualPunishMoney) {
		this.actualPunishMoney = actualPunishMoney;
	}
	
	
	
	
	public String getIsChoosed() {
		return isChoosed;
	}
	public void setIsChoosed(String isChoosed) {
		this.isChoosed = isChoosed;
	}
	public String getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	
	public Integer getInspectBillId() {
		return inspectBillId;
	}
	public void setInspectBillId(Integer inspectBillId) {
		this.inspectBillId = inspectBillId;
	}
	public InspectKind getCheckKind() {
		return checkKind;
	}
	public void setCheckKind(InspectKind checkKind) {
		this.checkKind = checkKind;
	}
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getCheckItemName() {
		return checkItemName;
	}
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getResponsibilityPersonM() {
		return responsibilityPersonM;
	}

	public void setResponsibilityPersonM(Integer responsibilityPersonM) {
		this.responsibilityPersonM = responsibilityPersonM;
	}

	public Integer getResponsibilityPersonW() {
		return responsibilityPersonW;
	}

	public void setResponsibilityPersonW(Integer responsibilityPersonW) {
		this.responsibilityPersonW = responsibilityPersonW;
	}


	private static final long serialVersionUID = 1L;


	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}
}
