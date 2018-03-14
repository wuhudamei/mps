
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizConstructionSchedule extends DataEntity<BizConstructionSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String projectMode;
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	private String isOldHouse;
	private String constructionScheduleName;
	private String sort;
	private String normalCompletionDays;
	private String lateCompletionDays;
	private String isEnable;
	
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