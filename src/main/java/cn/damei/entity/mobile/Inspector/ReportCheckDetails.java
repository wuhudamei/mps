package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class ReportCheckDetails extends DataEntity2<ReportCheckDetails>{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String qcCheckItemName;
	private String isPassed;
	private String isWarned;
	private String mnagerPerson;
	private String workGroupPerson;
	private String taskName;
	private String isLocaleRepaire;
	private String isLimitDateRepaire;
	private Date limitDate;
	private String limitDateRepaireCheckStyle;
	private String isPunishMoney;
	private Double punishMoneyAmountReal;
	private Integer pmPunishScore;
	private Double workerPunishAmount;
	private Integer workerPunishScore;
	private Double qcPunishAmount;
	private Integer qcPunishScore;
	private List<ReportCheckBreak> reportCheckBreakList;
	
	
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
