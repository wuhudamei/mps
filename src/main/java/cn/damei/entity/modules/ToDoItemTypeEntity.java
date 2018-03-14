package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;
/**
 * 待办事项分类表
 * @author lzm
 * @version 2017-7-14
 */
public class ToDoItemTypeEntity extends  DataEntity<ToDoItemTypeEntity>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rowNo;
	private Integer storeId;
	private String storeName;
	private String projectMode;
	private String toDoItemCode;
	private String toDoItemName;
	private Integer daysToRemind;
	private String businessStep;
	private String isToRemind;
	private String toDoItemGenerateStyle;
	private String toDoItemRemarks;
	private Integer sortIndex;
	private String relatedBusinessType;
	private Integer relatedBusinessId;
	
	
	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}
	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public Integer getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getBusinessStep() {
		return businessStep;
	}
	public void setBusinessStep(String businessStep) {
		this.businessStep = businessStep;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getToDoItemCode() {
		return toDoItemCode;
	}
	public void setToDoItemCode(String toDoItemCode) {
		this.toDoItemCode = toDoItemCode;
	}
	public String getToDoItemName() {
		return toDoItemName;
	}
	public void setToDoItemName(String toDoItemName) {
		this.toDoItemName = toDoItemName;
	}
	public Integer getDaysToRemind() {
		return daysToRemind;
	}
	public void setDaysToRemind(Integer daysToRemind) {
		this.daysToRemind = daysToRemind;
	}
	public String getIsToRemind() {
		return isToRemind;
	}
	public void setIsToRemind(String isToRemind) {
		this.isToRemind = isToRemind;
	}
	public String getToDoItemGenerateStyle() {
		return toDoItemGenerateStyle;
	}
	public void setToDoItemGenerateStyle(String toDoItemGenerateStyle) {
		this.toDoItemGenerateStyle = toDoItemGenerateStyle;
	}
	public String getToDoItemRemarks() {
		return toDoItemRemarks;
	}
	public void setToDoItemRemarks(String toDoItemRemarks) {
		this.toDoItemRemarks = toDoItemRemarks;
	}
	
	
}
