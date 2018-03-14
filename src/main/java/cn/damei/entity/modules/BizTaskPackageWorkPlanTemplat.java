/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import cn.damei.common.persistence.DataEntity;

/**
 * 任务包派工计划模板Entity
 * @author chy
 * @version 2016-09-03
 */
public class BizTaskPackageWorkPlanTemplat extends DataEntity<BizTaskPackageWorkPlanTemplat> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店
	private String projectMode; //工程模式
	private String isNewHouse;		// 新旧房
	private String templatName;		// 模板名称
	private String workSchedule;		// 工期天数
	private String status;		// 模板状态
	private List<BizTaskPackageWorkPlanTemplatRel> bizTaskPackageWorkPlanTemplatRelList = Lists.newArrayList();		// 子表列表
	
	public BizTaskPackageWorkPlanTemplat() {
		super();
	}

	public BizTaskPackageWorkPlanTemplat(String id){
		super(id);
	}

	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@Length(min=0, max=11, message="门店长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=1, message="新旧房长度必须介于 0 和 1 之间")
	public String getIsNewHouse() {
		return isNewHouse;
	}

	public void setIsNewHouse(String isNewHouse) {
		this.isNewHouse = isNewHouse;
	}
	
	@Length(min=0, max=100, message="模板名称长度必须介于 0 和 100 之间")
	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}
	
	@Length(min=0, max=11, message="工期天数长度必须介于 0 和 11 之间")
	public String getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(String workSchedule) {
		this.workSchedule = workSchedule;
	}
	
	@Length(min=0, max=1, message="模板状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<BizTaskPackageWorkPlanTemplatRel> getBizTaskPackageWorkPlanTemplatRelList() {
		return bizTaskPackageWorkPlanTemplatRelList;
	}

	public void setBizTaskPackageWorkPlanTemplatRelList(List<BizTaskPackageWorkPlanTemplatRel> bizTaskPackageWorkPlanTemplatRelList) {
		this.bizTaskPackageWorkPlanTemplatRelList = bizTaskPackageWorkPlanTemplatRelList;
	}
}