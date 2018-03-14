package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class InspectCheckTimesEntity  extends DataEntity<InspectCheckTimesEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer billId;
	private String qcBillType;
	private String isRecheck;
	private String qcBillStatus;

	private Integer storeId; //门店
	private String projectMode;
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	private  String inspectName;//质检员
	private Integer inspectId;//质检员id
	private Date  checkStartDate;//提交报告查询开始日期
	private Date checkEndDate;//提交报告查询结束日期
	private Integer checkConstructionSiteTimes;//检查工地次数
	private Integer signTimes;//签到次数
	private Integer inspectReportTimes;//质检报告次数
	private  Integer randomCheckReportTimes;//抽检报告次数
	private Integer  aboutCheckReportTimes;//约检报告次数
	private Integer recheckReportTimes;//复检报告次数
	private Integer recheckTimes;//复检次数
	private Integer fineTimes;//罚款次数
	private Integer findTotalMoney;//罚款总金额
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getInspectName() {
		return inspectName;
	}
	public void setInspectName(String inspectName) {
		this.inspectName = inspectName;
	}
	public Integer getInspectId() {
		return inspectId;
	}
	public void setInspectId(Integer inspectId) {
		this.inspectId = inspectId;
	}
	public Date getCheckStartDate() {
		return checkStartDate;
	}
	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}
	public Date getCheckEndDate() {
		return checkEndDate;
	}
	public void setCheckEndDate(Date checkEndDate) {
		this.checkEndDate = checkEndDate;
	}
	public Integer getCheckConstructionSiteTimes() {
		return checkConstructionSiteTimes;
	}
	public void setCheckConstructionSiteTimes(Integer checkConstructionSiteTimes) {
		this.checkConstructionSiteTimes = checkConstructionSiteTimes;
	}
	public Integer getSignTimes() {
		return signTimes;
	}
	public void setSignTimes(Integer signTimes) {
		this.signTimes = signTimes;
	}
	public Integer getInspectReportTimes() {
		return inspectReportTimes;
	}
	public void setInspectReportTimes(Integer inspectReportTimes) {
		this.inspectReportTimes = inspectReportTimes;
	}
	public Integer getRandomCheckReportTimes() {
		return randomCheckReportTimes;
	}
	public void setRandomCheckReportTimes(Integer randomCheckReportTimes) {
		this.randomCheckReportTimes = randomCheckReportTimes;
	}
	public Integer getAboutCheckReportTimes() {
		return aboutCheckReportTimes;
	}
	public void setAboutCheckReportTimes(Integer aboutCheckReportTimes) {
		this.aboutCheckReportTimes = aboutCheckReportTimes;
	}
	public Integer getRecheckReportTimes() {
		return recheckReportTimes;
	}
	public void setRecheckReportTimes(Integer recheckReportTimes) {
		this.recheckReportTimes = recheckReportTimes;
	}
	public Integer getRecheckTimes() {
		return recheckTimes;
	}
	public void setRecheckTimes(Integer recheckTimes) {
		this.recheckTimes = recheckTimes;
	}
	public Integer getFineTimes() {
		return fineTimes;
	}
	public void setFineTimes(Integer fineTimes) {
		this.fineTimes = fineTimes;
	}
	public Integer getFindTotalMoney() {
		return findTotalMoney;
	}
	public void setFindTotalMoney(Integer findTotalMoney) {
		this.findTotalMoney = findTotalMoney;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getQcBillType() {
		return qcBillType;
	}

	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}

	public String getIsRecheck() {
		return isRecheck;
	}

	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}

	public String getQcBillStatus() {
		return qcBillStatus;
	}

	public void setQcBillStatus(String qcBillStatus) {
		this.qcBillStatus = qcBillStatus;
	}
}
