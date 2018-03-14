package cn.damei.entity.mobile.home;

//import cn.damei.entity.mobile.home.CheckBreak;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 质检报告 检查项 Entity
 * @author wyb
 * @version 2016-11-16
 */
public class CheckItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qcBillItemId; //质检项id
	private Integer qcCheckItemId; //检查项id
	private String qcCheckItemName; //检查内容
	private String isPassed; //是否合格

	private Report report;
	
	
	private String isWarned; //是否警告
	private String isLocaleRepaire; //是否现场整改
	private String isLimitDateRepaire; //是否限期整改
	private Date limitDate; //整改期限
	private String limitDateRepaireCheckStyle; //限期整改  整改方式
	private String isPunishMoney; //是否罚款
	private Double punishMoneyAmountReal; //罚款金额
	private Integer pmPunishScore; //罚款分数
	private Double workerPunishAmount; //工人罚款金额
	private Integer workerPunishScore; //罚款分数
	private Double qcPunishAmount; //质检罚款金额
	private Integer qcPunishScore; //罚款分数
	private String checkStyle; //检查方式
	
	private List<CheckBreak> checkBreakList; //违规形式列表
	
	public Integer getQcBillItemId() {
		return qcBillItemId;
	}

	public void setQcBillItemId(Integer qcBillItemId) {
		this.qcBillItemId = qcBillItemId;
	}

	public Integer getQcCheckItemId() {
		return qcCheckItemId;
	}

	public void setQcCheckItemId(Integer qcCheckItemId) {
		this.qcCheckItemId = qcCheckItemId;
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

	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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

	public String getCheckStyle() {
		return checkStyle;
	}

	public void setCheckStyle(String checkStyle) {
		this.checkStyle = checkStyle;
	}

	public List<CheckBreak> getCheckBreakList() {
		return checkBreakList;
	}

	public void setCheckBreakList(List<CheckBreak> checkBreakList) {
		this.checkBreakList = checkBreakList;
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
	
	
	
}