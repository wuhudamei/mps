/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 施工项类型Entity
 * @author 梅浩
 * @version 2016-11-15
 */
public class ProjectItemType extends DataEntity<ProjectItemType> {
	
	private static final long serialVersionUID = 1L;
	private Integer projectItemTypeId;		// id -- '
	private String projectIntemTypeName;		// 分类名称 -- '
	private String status;		// 状态 -- '0.停用；1.启用
	private Integer updateMan;		// 更新人员工id -- '
	
	public ProjectItemType() {
		super();
	}

	public ProjectItemType(String id){
		super(id);
	}

	public Integer getProjectItemTypeId() {
		return projectItemTypeId;
	}

	public void setProjectItemTypeId(Integer projectItemTypeId) {
		this.projectItemTypeId = projectItemTypeId;
	}
	
	@Length(min=0, max=100, message="分类名称 -- '长度必须介于 0 和 100 之间")
	public String getProjectIntemTypeName() {
		return projectIntemTypeName;
	}

	public void setProjectIntemTypeName(String projectIntemTypeName) {
		this.projectIntemTypeName = projectIntemTypeName;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(Integer updateMan) {
		this.updateMan = updateMan;
	}
	
}