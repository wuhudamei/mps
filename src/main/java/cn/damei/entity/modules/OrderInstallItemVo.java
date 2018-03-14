package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class OrderInstallItemVo extends DataEntity2<Order> {


	private static final long serialVersionUID = 1L;



	private Integer orderId;
	private Integer projectInstallItemId;
	private String projectInstallItemName;
	private Integer installItemSequence;
	private Integer daysToApplyInto;
	private Integer daysPlanInto;
	private Integer daysPalnComplete;
	private String isGeneratedOrdeInstallPlan;
	private String isOn;
	private String isChoosed;
	private String status;
	private Date planIntoDate;
	private Date applyIntoDate;
	private Date realIntoDate;
	private String projectMode;
	private Integer daysPlanChecksize;
	private String isToChecksize;
	private String installMode;
	private String installstatus;
	private Date statusDate;
	
	private String installModeFlag;
	private String installStatus;
	private Date planCompleteDate;
	
	private String isShowInstallDescription;
	private String installDescription;
	private Date allowApplyChecksizeDate;
	
	public Date getPlanCompleteDate() {
		return planCompleteDate;
	}

	public void setPlanCompleteDate(Date planCompleteDate) {
		this.planCompleteDate = planCompleteDate;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public void setInstallStatus(String installStatus) {
		this.installStatus = installStatus;
	}

	public String getInstallModeFlag() {
		return installModeFlag;
	}

	public void setInstallModeFlag(String installModeFlag) {
		this.installModeFlag = installModeFlag;
	}

	public Integer getDaysPlanChecksize() {
		return daysPlanChecksize;
	}

	public void setDaysPlanChecksize(Integer daysPlanChecksize) {
		this.daysPlanChecksize = daysPlanChecksize;
	}

	public String getInstallstatus() {
		return installstatus;
	}

	public void setInstallstatus(String installstatus) {
		this.installstatus = installstatus;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getIsToChecksize() {
		return isToChecksize;
	}

	public void setIsToChecksize(String isToChecksize) {
		this.isToChecksize = isToChecksize;
	}

	public String getInstallMode() {
		return installMode;
	}

	public void setInstallMode(String installMode) {
		this.installMode = installMode;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Date getPlanIntoDate() {
		return planIntoDate;
	}

	public void setPlanIntoDate(Date planIntoDate) {
		this.planIntoDate = planIntoDate;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getRealIntoDate() {
		return realIntoDate;
	}

	public void setRealIntoDate(Date realIntoDate) {
		this.realIntoDate = realIntoDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsChoosed() {
		return isChoosed;
	}

	public void setIsChoosed(String isChoosed) {
		this.isChoosed = isChoosed;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProjectInstallItemId() {
		return projectInstallItemId;
	}

	public void setProjectInstallItemId(Integer projectInstallItemId) {
		this.projectInstallItemId = projectInstallItemId;
	}

	public String getProjectInstallItemName() {
		return projectInstallItemName;
	}

	public void setProjectInstallItemName(String projectInstallItemName) {
		this.projectInstallItemName = projectInstallItemName;
	}

	public Integer getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(Integer installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	public Integer getDaysToApplyInto() {
		return daysToApplyInto;
	}

	public void setDaysToApplyInto(Integer daysToApplyInto) {
		this.daysToApplyInto = daysToApplyInto;
	}

	public Integer getDaysPlanInto() {
		return daysPlanInto;
	}

	public void setDaysPlanInto(Integer daysPlanInto) {
		this.daysPlanInto = daysPlanInto;
	}

	public Integer getDaysPalnComplete() {
		return daysPalnComplete;
	}

	public void setDaysPalnComplete(Integer daysPalnComplete) {
		this.daysPalnComplete = daysPalnComplete;
	}

	public String getIsGeneratedOrdeInstallPlan() {
		return isGeneratedOrdeInstallPlan;
	}

	public void setIsGeneratedOrdeInstallPlan(String isGeneratedOrdeInstallPlan) {
		this.isGeneratedOrdeInstallPlan = isGeneratedOrdeInstallPlan;
	}

	public String getIsOn() {
		return isOn;
	}

	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}

	public String getIsShowInstallDescription() {
		return isShowInstallDescription;
	}

	public void setIsShowInstallDescription(String isShowInstallDescription) {
		this.isShowInstallDescription = isShowInstallDescription;
	}

	public String getInstallDescription() {
		return installDescription;
	}

	public void setInstallDescription(String installDescription) {
		this.installDescription = installDescription;
	}

	public Date getAllowApplyChecksizeDate() {
		return allowApplyChecksizeDate;
	}

	public void setAllowApplyChecksizeDate(Date allowApplyChecksizeDate) {
		this.allowApplyChecksizeDate = allowApplyChecksizeDate;
	}

}
