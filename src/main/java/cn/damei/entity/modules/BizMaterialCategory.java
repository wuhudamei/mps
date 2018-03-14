/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 材料类别管理Entity
 * @author lc
 * @version 2016-09-08
 */
public class BizMaterialCategory extends DataEntity<BizMaterialCategory> {
	
	private static final long serialVersionUID = 1L;
	private String categoryNo;		// 材料类别编号
	private String categoryName;		// 材料类别名称
	private String materialTypeId;		// 所属材料分类id：主材/辅材
	private String status;		// 状态：0停用 1启用
	
	public BizMaterialCategory() {
		super();
	}

	public BizMaterialCategory(String id){
		super(id);
	}

	@Length(min=1, max=18, message="材料类别编号长度必须介于 1 和 18 之间")
	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	@Length(min=1, max=255, message="材料类别名称长度必须介于 1 和 255 之间")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Length(min=0, max=1, message="所属材料分类id：主材/辅材长度必须介于 0 和 1 之间")
	public String getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(String materialTypeId) {
		this.materialTypeId = materialTypeId;
	}
	
	@Length(min=1, max=1, message="状态：0停用 1启用长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}