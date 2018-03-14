
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class ProjectItemType extends DataEntity<ProjectItemType> {
	
	private static final long serialVersionUID = 1L;
	private Integer projectItemTypeId;
	private String projectIntemTypeName;
	private String status;
	private Integer updateMan;
	
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