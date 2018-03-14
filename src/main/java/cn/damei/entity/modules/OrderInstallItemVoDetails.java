package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.modules.OrderDetails;


public class OrderInstallItemVoDetails extends  DataEntity2<OrderDetails>{


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
	
}
