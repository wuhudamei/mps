package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检 检查项
 * @author梅浩
 *
 */
public class InspectItem extends DataEntity2<InspectItem> {

	
	
	private Integer inspectBillId;//质检单id
	private Integer itemId;
	private Integer orderId;//结算用
	private Integer  managerId;//经理id
	private Integer  responsibilityPersonM;//责任项目经理id
	private Integer  responsibilityPersonW;//责任工人组id
	private Integer  taskPackageTemplatId;//任务包模板ID
	private String  managerName;//经理名称
	private String  projectMode;//工程模式
	private Integer  pqcId;//经理id

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
	private Integer  relatedBillCheckItemId;//关联质检单检查项id
	
	private String isRequired;//是否必检   0否 1 是
	private Integer checkItemId;//检查项id
	private String checkItemName;//检查项名字
	private String isChoosed;//是否选过   1 是 0 否
	//页面VO数据属性 ,处理方式等
	private String isOk; //是否合格
	private Double preScores;//原有分数
	private Double actualScores;//实际得分
	private String  isWarn;//是否警告
	
	private String  isLocatedChange;//是否现场整改
	private String  isLimitDateChange;//是否限期整改
	private Date limitDate;//限期整改日期
	private String limitChangeWay;//限期整改方式   0线上   1线下
	private String  isPunishMoney;//是否罚款
	private Double  prePunishMoney;//检查项默认罚款金额
	private Double  actualPunishMoney;//实际罚款金额
	private InspectKind   checkKind;//检查分类对象
	
	//17-2-16  不合格项弹出框更改 
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

	/**
	 * 
	 */
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
