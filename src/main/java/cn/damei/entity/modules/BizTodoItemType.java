
package cn.damei.entity.modules;

import cn.damei.common.utils.UserUtils;
import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizTodoItemType extends DataEntity<BizTodoItemType> {
	
	private static final long serialVersionUID = 1L;
	private String storeId= UserUtils.getUser().getStoreId();
	private String projectMode;
	private String todoItemCode;
	private String todoItemName;
	private String daysToRemind;
	private String sortIndex;
	private String businessStep;
	private String isToRemind;
	private String todoItemGenerateStyle;
	private String todoItemRemarks;
	private String relatedBusinessType;
	private String relatedBusinessId;


	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}

	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}

	public String getRelatedBusinessId() {
		return relatedBusinessId;
	}

	public void setRelatedBusinessId(String relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public BizTodoItemType() {
		super();
	}

	public BizTodoItemType(String id){
		super(id);
	}

	@Length(min=0, max=11, message="store_id长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="project_mode长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="todo_item_code长度必须介于 0 和 100 之间")
	public String getTodoItemCode() {
		return todoItemCode;
	}

	public void setTodoItemCode(String todoItemCode) {
		this.todoItemCode = todoItemCode;
	}
	
	@Length(min=0, max=100, message="todo_item_name长度必须介于 0 和 100 之间")
	public String getTodoItemName() {
		return todoItemName;
	}

	public void setTodoItemName(String todoItemName) {
		this.todoItemName = todoItemName;
	}
	
	@Length(min=0, max=11, message="days_to_remind长度必须介于 0 和 11 之间")
	public String getDaysToRemind() {
		return daysToRemind;
	}

	public void setDaysToRemind(String daysToRemind) {
		this.daysToRemind = daysToRemind;
	}
	
	@Length(min=0, max=100, message="ҵ长度必须介于 0 和 100 之间")
	public String getBusinessStep() {
		return businessStep;
	}

	public void setBusinessStep(String businessStep) {
		this.businessStep = businessStep;
	}
	
	@Length(min=0, max=1, message="is_to_remind长度必须介于 0 和 1 之间")
	public String getIsToRemind() {
		return isToRemind;
	}

	public void setIsToRemind(String isToRemind) {
		this.isToRemind = isToRemind;
	}
	
	@Length(min=0, max=10, message="todo_item_generate_style长度必须介于 0 和 10 之间")
	public String getTodoItemGenerateStyle() {
		return todoItemGenerateStyle;
	}

	public void setTodoItemGenerateStyle(String todoItemGenerateStyle) {
		this.todoItemGenerateStyle = todoItemGenerateStyle;
	}
	
	@Length(min=0, max=255, message="todo_item_remarks长度必须介于 0 和 255 之间")
	public String getTodoItemRemarks() {
		return todoItemRemarks;
	}

	public void setTodoItemRemarks(String todoItemRemarks) {
		this.todoItemRemarks = todoItemRemarks;
	}
	
}