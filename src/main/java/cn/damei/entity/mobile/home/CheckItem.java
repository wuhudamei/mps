package cn.damei.entity.mobile.home;



import java.io.Serializable;
import java.util.Date;
import java.util.List;




public class CheckItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qcBillItemId;
	private Integer qcCheckItemId;
	private String qcCheckItemName;
	private String isPassed;

	private Report report;
	
	
	private String isWarned;
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
	private String checkStyle;
	
	private List<CheckBreak> checkBreakList;
	
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