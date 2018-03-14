/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 选材单材料表Entity
 * @author wyb
 * @version 2017-06-13
 */
public class BizMaterialsChoiceCategory extends DataEntity2<BizMaterialsChoiceCategory> {
	
	private static final long serialVersionUID = 1L;
	private Integer parentId;		// 所属父级id
	private Integer categoryLevel;		// 类目级别
	private String categoryCode;		// 类型编码
	private String categoryName;		// 类目名称
	private Integer storeId;		// 门店id
	 
	
	public BizMaterialsChoiceCategory() {
		super();
	}

	public BizMaterialsChoiceCategory(Integer id){
		super(id);
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	
	
}