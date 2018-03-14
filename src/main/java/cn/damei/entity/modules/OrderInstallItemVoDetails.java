package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.modules.OrderDetails;

/**
 * 订单安装项vo
 * @author 梅浩
 *
 */
public class OrderInstallItemVoDetails extends  DataEntity2<OrderDetails>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//主键在爷爷里
	
	private Integer orderId;//订单外键id
	private Integer projectInstallItemId;//关联的模板安装项id
	private String projectInstallItemName;//关联的模板安装项name
	private Integer installItemSequence;		// 安装项顺序
	private Integer daysToApplyInto;		// 开工第几天申请
	private Integer daysPlanInto;		// 开工第几天进场
	private Integer daysPalnComplete;		// 开工第几天完成
	private String isGeneratedOrdeInstallPlan;//是否生成计划
	private String isOn;//是否启用  0:停用  1:启用
	private String isChoosed;//修改订单回显判断字段
	private String status;//安装项的状态    //作为安装项计划中为  1,2,3   作为 订单安装项, 为是否可选
	private Date planIntoDate;
	private Date applyIntoDate;
	private Date realIntoDate;
	private String projectMode;//工程模式
	
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
