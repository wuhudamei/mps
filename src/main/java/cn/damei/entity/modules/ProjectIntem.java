/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 施工项Entity
 * @author 梅浩
 * @version 2016-11-15
 */
public class ProjectIntem extends DataEntity<ProjectIntem> {
	
	private static final long serialVersionUID = 1L;
	private String projectItemId;		// id
	private Integer projectIntemTypeId;		// 施工项分类
	private String projectIntemMold;		// 施工项类型
	private String projectIntemCode;		// 施工项编码
	private String projectIntemName;		// 施工项名称
	private String projectIntemUnit;		// 施工项计量单位
	private String projectType;		// 施工类型
	private Integer groupType;		// 套餐类型
	private Integer status;		// 状态
	private String projectIntemDetail;		// 施工项详情
	private String projectIntemTypeName;
	private String usingPlatform;//使用平台
	private String valuationMethod;//计价方式
	private String subordinateCategory;//所属类别
	private String isDefault;//是否是默认项
	
	
	
	
	public String getUsingPlatform() {
		return usingPlatform;
	}

	public void setUsingPlatform(String usingPlatform) {
		this.usingPlatform = usingPlatform;
	}

	public String getValuationMethod() {
		return valuationMethod;
	}

	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public String getSubordinateCategory() {
		return subordinateCategory;
	}

	public void setSubordinateCategory(String subordinateCategory) {
		this.subordinateCategory = subordinateCategory;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getProjectIntemTypeName() {
		return projectIntemTypeName;
	}

	public void setProjectIntemTypeName(String projectIntemTypeName) {
		this.projectIntemTypeName = projectIntemTypeName;
	}

	public ProjectIntem() {
		super();
	}

	public ProjectIntem(String id){
		super(id);
	}

	@Length(min=1, max=11, message="id长度必须介于 1 和 11 之间")
	public String getProjectItemId() {
		return projectItemId;
	}

	public void setProjectItemId(String projectItemId) {
		this.projectItemId = projectItemId;
	}
	
	public Integer getProjectIntemTypeId() {
		return projectIntemTypeId;
	}

	public void setProjectIntemTypeId(Integer projectIntemTypeId) {
		this.projectIntemTypeId = projectIntemTypeId;
	}
	
	
	public String getProjectIntemMold() {
		return projectIntemMold;
	}

	public void setProjectIntemMold(String projectIntemMold) {
		this.projectIntemMold = projectIntemMold;
	}

	@Length(min=0, max=64, message="施工项编码长度必须介于 0 和 64 之间")
	public String getProjectIntemCode() {
		return projectIntemCode;
	}

	public void setProjectIntemCode(String projectIntemCode) {
		this.projectIntemCode = projectIntemCode;
	}
	
	@Length(min=0, max=100, message="施工项名称长度必须介于 0 和 100 之间")
	public String getProjectIntemName() {
		return projectIntemName;
	}

	public void setProjectIntemName(String projectIntemName) {
		this.projectIntemName = projectIntemName;
	}
	
	@Length(min=0, max=64, message="施工项计量单位长度必须介于 0 和 64 之间")
	public String getProjectIntemUnit() {
		return projectIntemUnit;
	}

	public void setProjectIntemUnit(String projectIntemUnit) {
		this.projectIntemUnit = projectIntemUnit;
	}
	
	@Length(min=1, max=100, message="施工类型长度必须介于 1 和 100 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="施工项详情长度必须介于 0 和 255 之间")
	public String getProjectIntemDetail() {
		return projectIntemDetail;
	}

	public void setProjectIntemDetail(String projectIntemDetail) {
		this.projectIntemDetail = projectIntemDetail;
	}
	
}