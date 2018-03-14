/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 任务包派工计划模板Entity
 * @author chy
 * @version 2016-09-03
 */
public class BizTaskPackageWorkPlanTemplatRel extends DataEntity<BizTaskPackageWorkPlanTemplatRel> {
	
	private static final long serialVersionUID = 1L;
	private BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat;		// 任务包派工计划模板id 父类
	private String storeId;		// 门店id
	private String packageOrder;		// 顺序
	private String taskPackageId;		// 任务包名称
	private String beginDay;		// 开工第几天开始
	private String endDay;		// 开工第几天结束
	
	public BizTaskPackageWorkPlanTemplatRel() {
		super();
	}

	public BizTaskPackageWorkPlanTemplatRel(String id){
		super(id);
	}

	public BizTaskPackageWorkPlanTemplatRel(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat){
		this.bizTaskPackageWorkPlanTemplat = bizTaskPackageWorkPlanTemplat;
	}

	@Length(min=0, max=11, message="任务包派工计划模板id长度必须介于 0 和 11 之间")
	public BizTaskPackageWorkPlanTemplat getBizTaskPackageWorkPlanTemplat() {
		return bizTaskPackageWorkPlanTemplat;
	}

	public void setBizTaskPackageWorkPlanTemplat(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat) {
		this.bizTaskPackageWorkPlanTemplat = bizTaskPackageWorkPlanTemplat;
	}
	
	@Length(min=0, max=11, message="门店id长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=11, message="顺序长度必须介于 0 和 11 之间")
	public String getPackageOrder() {
		return packageOrder;
	}

	public void setPackageOrder(String packageOrder) {
		this.packageOrder = packageOrder;
	}
	
	@Length(min=0, max=11, message="任务包名称长度必须介于 0 和 11 之间")
	public String getTaskPackageId() {
		return taskPackageId;
	}

	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	
	@Length(min=0, max=11, message="开工第几天开始长度必须介于 0 和 11 之间")
	public String getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(String beginDay) {
		this.beginDay = beginDay;
	}
	
	@Length(min=0, max=11, message="开工第几天结束长度必须介于 0 和 11 之间")
	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	
}