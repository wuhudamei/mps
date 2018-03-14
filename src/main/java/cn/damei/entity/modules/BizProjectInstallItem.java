/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程安装项Entity
 * 
 * @author 梅浩
 * @version 2016-09-01
 */
public class BizProjectInstallItem extends DataEntity<BizProjectInstallItem> {

	private static final long serialVersionUID = 1L;
	private String storeId; // 门店id
	private String projectMode;// 工程模式
	private String installMode;// 安装模式
	private String installItemName; // 安装项名称
	private Integer installItemSequence; // 安装项顺序
	private Integer workApplyDay; // 开工第几天申请
	private Integer workEnterDay; // 开工第几天进场
	private Integer workCompleteDay; // 开工第几天完成
	private String isOn; // 停用或启用 (默认停用)
	private String daysPlanChecksize; // 开工第几天复尺
	private String isToChecksize = "0"; // 是否需要复尺 (默认否)

	private String isShowInstallDescription = "0"; // 是否展示安装说明
	private String installDescription; // 安装说明

	private Integer orderInstallItemId;

	public String getInstallMode() {
		return installMode;
	}

	public void setInstallMode(String installMode) {
		this.installMode = installMode;
	}

	public String getDaysPlanChecksize() {
		return daysPlanChecksize;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public void setDaysPlanChecksize(String daysPlanChecksize) {
		this.daysPlanChecksize = daysPlanChecksize;
	}

	public String getIsToChecksize() {
		return isToChecksize;
	}

	public void setIsToChecksize(String isToChecksize) {
		this.isToChecksize = isToChecksize;
	}

	public BizProjectInstallItem() {
		super();
	}

	public BizProjectInstallItem(String id) {
		super(id);
	}

	@Length(min = 1, max = 64, message = "门店id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Length(min = 1, max = 100, message = "安装项名称长度必须介于 1 和 100 之间")
	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	@NotNull(message = "安装项顺序不能为空")
	public Integer getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(Integer installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	@NotNull(message = "开工第几天申请不能为空")
	public Integer getWorkApplyDay() {
		return workApplyDay;
	}

	public void setWorkApplyDay(Integer workApplyDay) {
		this.workApplyDay = workApplyDay;
	}

	@NotNull(message = "开工第几天进场不能为空")
	public Integer getWorkEnterDay() {
		return workEnterDay;
	}

	public void setWorkEnterDay(Integer workEnterDay) {
		this.workEnterDay = workEnterDay;
	}

	@NotNull(message = "开工第几天完成不能为空")
	public Integer getWorkCompleteDay() {
		return workCompleteDay;
	}

	public void setWorkCompleteDay(Integer workCompleteDay) {
		this.workCompleteDay = workCompleteDay;
	}

	@Length(min = 1, max = 1, message = "停用或启用 (默认停用)长度必须介于 1 和 1 之间")
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

	public Integer getOrderInstallItemId() {
		return orderInstallItemId;
	}

	public void setOrderInstallItemId(Integer orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}

}