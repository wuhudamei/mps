/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程进度节点Entity
 * @author 魏建勇
 * @version 2016-09-05
 */
public class BizConstructionSchedule extends DataEntity<BizConstructionSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店id
	private String projectMode;//工程模式
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	private String isOldHouse;		// 是否老房
	private String constructionScheduleName;		// 进度节点名称
	private String sort;		// 进度节点顺序
	private String normalCompletionDays;		// 正常完工天数
	private String lateCompletionDays;		// 最晚完工天数
	private String isEnable;		// 启用标记
	
	public BizConstructionSchedule() {
		super();
	}

	public BizConstructionSchedule(String id){
		super(id);
	}

	@Length(min=1, max=64, message="门店id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=1, message="是否老房长度必须介于 1 和 1 之间")
	public String getIsOldHouse() {
		return isOldHouse;
	}

	public void setIsOldHouse(String isOldHouse) {
		this.isOldHouse = isOldHouse;
	}
	
	@Length(min=1, max=100, message="进度节点名称长度必须介于 1 和 100 之间")
	public String getConstructionScheduleName() {
		return constructionScheduleName;
	}

	public void setConstructionScheduleName(String constructionScheduleName) {
		this.constructionScheduleName = constructionScheduleName;
	}
	
	@Length(min=1, max=2, message="进度节点顺序长度必须介于 1 和 2 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=2, message="正常完工天数长度必须介于 1 和 2 之间")
	public String getNormalCompletionDays() {
		return normalCompletionDays;
	}

	public void setNormalCompletionDays(String normalCompletionDays) {
		this.normalCompletionDays = normalCompletionDays;
	}
	
	@Length(min=1, max=2, message="最晚完工天数长度必须介于 1 和 2 之间")
	public String getLateCompletionDays() {
		return lateCompletionDays;
	}

	public void setLateCompletionDays(String lateCompletionDays) {
		this.lateCompletionDays = lateCompletionDays;
	}
	
	@Length(min=1, max=1, message="启用标记长度必须介于 1 和 1 之间")
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

}