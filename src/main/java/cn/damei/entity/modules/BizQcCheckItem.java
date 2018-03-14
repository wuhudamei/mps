/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 检查项Entity
 * @author wyb
 * @version 2016-10-27
 */
public class BizQcCheckItem extends DataEntity2<BizQcCheckItem> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private Integer qcCheckKindId;		// 检查分类id -- '
	private String qcCheckKind;
	private String qcCheckItemName;		// 检查项名称 -- '
	private Double itemScore;		// 项目分数 -- '
	private String isRedline;		// 是否红线 -- '0.否；1.是
	private String isRequired;		// 是否必检 -- '0.否；1.是
	private String status;		// 状态 -- '0.停用；1.启用
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	private Double punishAmount;		// 处罚项目经理金额 -- '
	private Integer pmPunishScore; //扣项目经理分值
	private Double workerPunishAmount; //处罚工人金额
	private Integer workerPunishScore; //扣工人分值
	private Double qcPunishAmount;	//处罚质检员分值
	private Integer qcPunishScore; //扣质检员分值
	private String taskPackageTemplatId;		// 任务包模板ID '
	private String templatName;		// 任务包模板名称 '
	
	public String getQcCheckKind() {
		return qcCheckKind;
	}

	public void setQcCheckKind(String qcCheckKind) {
		this.qcCheckKind = qcCheckKind;
	}

	public BizQcCheckItem() {
		super();
	}

	public BizQcCheckItem(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getQcCheckKindId() {
		return qcCheckKindId;
	}

	public void setQcCheckKindId(Integer qcCheckKindId) {
		this.qcCheckKindId = qcCheckKindId;
	}
	
	@Length(min=0, max=100, message="检查项名称 -- '长度必须介于 0 和 100 之间")
	public String getQcCheckItemName() {
		return qcCheckItemName;
	}

	public void setQcCheckItemName(String qcCheckItemName) {
		this.qcCheckItemName = qcCheckItemName;
	}
	
	public Double getPunishAmount() {
		return punishAmount;
	}

	public void setPunishAmount(Double punishAmount) {
		this.punishAmount = punishAmount;
	}
	
	public Double getItemScore() {
		return itemScore;
	}

	public void setItemScore(Double itemScore) {
		this.itemScore = itemScore;
	}
	
	@Length(min=0, max=1, message="是否红线 -- '长度必须介于 0 和 1 之间")
	public String getIsRedline() {
		return isRedline;
	}

	public void setIsRedline(String isRedline) {
		this.isRedline = isRedline;
	}
	
	@Length(min=0, max=1, message="是否必检 -- '长度必须介于 0 和 1之间")
	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}
	
	@Length(min=0, max=1, message="状态 -- '长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
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


	public String getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(String taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}
}