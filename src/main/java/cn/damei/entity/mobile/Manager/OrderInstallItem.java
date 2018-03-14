package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderInstallItem extends DataEntity2<OrderInstallItem> {

	private Integer id;


	private Integer orderId;


	private Integer projectInstallItemId;


	private String installItemName;


	private Integer installItemSequence;


	private Integer daysToApplyInto;


	private Integer daysPlanInto;


	private Integer daysPalnComplete;


	private String isGeneratedOrderInstallPlan;
	

	private Integer daysPlanChecksize;


	private String isToChecksize;

	private String remarks;


	private Date createDate;


	private Date updateDate;


	private String delFlag;
	
	

	private String installMode;
	

	public String getInstallMode() {
		return installMode;
	}

	public void setInstallMode(String installMode) {
		this.installMode = installMode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
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

	public String getIsGeneratedOrderInstallPlan() {
		return isGeneratedOrderInstallPlan;
	}

	public void setIsGeneratedOrderInstallPlan(String isGeneratedOrderInstallPlan) {
		this.isGeneratedOrderInstallPlan = isGeneratedOrderInstallPlan;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getDaysPlanChecksize() {
		return daysPlanChecksize;
	}

	public void setDaysPlanChecksize(Integer daysPlanChecksize) {
		this.daysPlanChecksize = daysPlanChecksize;
	}

	public String getIsToChecksize() {
		return isToChecksize;
	}

	public void setIsToChecksize(String isToChecksize) {
		this.isToChecksize = isToChecksize;
	}

}
