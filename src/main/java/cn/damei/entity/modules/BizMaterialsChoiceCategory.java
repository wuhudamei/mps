
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsChoiceCategory extends DataEntity2<BizMaterialsChoiceCategory> {
	
	private static final long serialVersionUID = 1L;
	private Integer parentId;
	private Integer categoryLevel;
	private String categoryCode;
	private String categoryName;
	private Integer storeId;
	 
	
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