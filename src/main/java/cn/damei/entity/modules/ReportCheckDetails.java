package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检报告vo
 * @author Administrator
 *
 */
public class ReportCheckDetails extends DataEntity2<ReportCheckDetails>{

	private static final long serialVersionUID = 1L;
	
	private Integer id; //id
	private Integer qcBillId; //质检单id
	private String qcCheckItemName; //检查项名称
	private Integer qcCheckKindId; //检查分类id
	private String qcCheckKindName; //检查分类
	private String mnagerPerson; //项目经理名称
	private String workGroupPerson; //工人组名称
	private String taskName; //任务包名称
	private String isPassed; //是否合格
	private String isWarned; //是否警告
	private String isLocaleRepaire; //是否现场整改
	private String isLimitDateRepaire; //是否限期整改
	private Date limitDate; //整改期限
	private String limitDateRepaireCheckStyle; //限期整改  整改方式
	private String isPunishMoney; //是否罚款
	private Double punishMoneyAmountReal; //项目经理罚款金额
	private Integer pmPunishScore; //项目经理罚款分数
	private Double workerPunishAmount; //工人罚款金额
	private Integer workerPunishScore; //工人罚款分数
	private Double qcPunishAmount; //质检罚款金额
	private Integer qcPunishScore; //质检罚款分数
	private List<ReportCheckBreak> reportCheckBreakList;
	
	
	public Integer getQcCheckKindId() {
		return qcCheckKindId;
	}
	public void setQcCheckKindId(Integer qcCheckKindId) {
		this.qcCheckKindId = qcCheckKindId;
	}
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}
	public String getQcCheckKindName() {
		return qcCheckKindName;
	}
	public void setQcCheckKindName(String qcCheckKindName) {
		this.qcCheckKindName = qcCheckKindName;
	}
	public List<ReportCheckBreak> getReportCheckBreakList() {
		return reportCheckBreakList;
	}
	public void setReportCheckBreakList(List<ReportCheckBreak> reportCheckBreakList) {
		this.reportCheckBreakList = reportCheckBreakList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQcCheckItemName() {
		return qcCheckItemName;
	}
	public void setQcCheckItemName(String qcCheckItemName) {
		this.qcCheckItemName = qcCheckItemName;
	}
	public String getIsPassed() {
		return isPassed;
	}
	public void setIdPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	public String getIsWarned() {
		return isWarned;
	}
	public void setIsWarned(String isWarned) {
		this.isWarned = isWarned;
	}
	public String getIsLocaleRepaire() {
		return isLocaleRepaire;
	}
	public void setIsLocaleRepaire(String isLocaleRepaire) {
		this.isLocaleRepaire = isLocaleRepaire;
	}
	public String getIsLimitDateRepaire() {
		return isLimitDateRepaire;
	}
	public void setIsLimitDateRepaire(String isLimitDateRepaire) {
		this.isLimitDateRepaire = isLimitDateRepaire;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getLimitDateRepaireCheckStyle() {
		return limitDateRepaireCheckStyle;
	}
	public void setLimitDateRepaireCheckStyle(String limitDateRepaireCheckStyle) {
		this.limitDateRepaireCheckStyle = limitDateRepaireCheckStyle;
	}
	public String getIsPunishMoney() {
		return isPunishMoney;
	}
	public void setIsPunishMoney(String isPunishMoney) {
		this.isPunishMoney = isPunishMoney;
	}
	public Double getPunishMoneyAmountReal() {
		return punishMoneyAmountReal;
	}
	public void setPunishMoneyAmountReal(Double punishMoneyAmountReal) {
		this.punishMoneyAmountReal = punishMoneyAmountReal;
	}
	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	public Integer getPmPunishScore() {
		return pmPunishScore;
	}
	public void setPmPunishScore(Integer pmPunishScore) {
		this.pmPunishScore = pmPunishScore;
	}
	public Double getWorkerPunishAmount() {
		return workerPunishAmount;
	}
	public void setWorkerPunishAmount(Double workerPunishAmount) {
		this.workerPunishAmount = workerPunishAmount;
	}
	public Integer getWorkerPunishScore() {
		return workerPunishScore;
	}
	public void setWorkerPunishScore(Integer workerPunishScore) {
		this.workerPunishScore = workerPunishScore;
	}
	public Double getQcPunishAmount() {
		return qcPunishAmount;
	}
	public void setQcPunishAmount(Double qcPunishAmount) {
		this.qcPunishAmount = qcPunishAmount;
	}
	public Integer getQcPunishScore() {
		return qcPunishScore;
	}
	public void setQcPunishScore(Integer qcPunishScore) {
		this.qcPunishScore = qcPunishScore;
	}


	public String getMnagerPerson() {
		return mnagerPerson;
	}

	public void setMnagerPerson(String mnagerPerson) {
		this.mnagerPerson = mnagerPerson;
	}

	public String getWorkGroupPerson() {
		return workGroupPerson;
	}

	public void setWorkGroupPerson(String workGroupPerson) {
		this.workGroupPerson = workGroupPerson;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
