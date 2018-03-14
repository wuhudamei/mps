package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单安装项vo
 * 
 * @author 梅浩
 *
 */
public class OrderInstallItemVo extends DataEntity2<Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 主键在爷爷里

	private Integer orderId;// 订单外键id
	private Integer projectInstallItemId;// 关联的模板安装项id
	private String projectInstallItemName;// 关联的模板安装项name
	private Integer installItemSequence; // 安装项顺序
	private Integer daysToApplyInto; // 开工第几天申请
	private Integer daysPlanInto; // 开工第几天进场
	private Integer daysPalnComplete; // 开工第几天完成
	private String isGeneratedOrdeInstallPlan;// 是否生成计划
	private String isOn;// 是否启用 0:停用 1:启用
	private String isChoosed;// 修改订单回显判断字段
	private String status;// 安装项的状态 //作为安装项计划中为 1,2,3 作为 订单安装项, 为是否可选
	private Date planIntoDate;
	private Date applyIntoDate;
	private Date realIntoDate;
	private String projectMode;// 工程模式
	private Integer daysPlanChecksize;// 开工第几天复尺
	private String isToChecksize;// 是需要复尺
	private String installMode;// 安装项
	private String installstatus;// 安装项状态
	private Date statusDate;// 安装项状态时间
	
	private String installModeFlag;// 安装模式标识
	private String installStatus;// 安装项状态
	private Date planCompleteDate;//计划完成时间
	
	private String isShowInstallDescription; // 是否展示安装说明
	private String installDescription; // 安装说明
	private Date allowApplyChecksizeDate;//可申请复尺日期
	
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
