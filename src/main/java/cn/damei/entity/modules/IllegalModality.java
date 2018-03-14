
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class IllegalModality extends DataEntity2<IllegalModality> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String storeName;
	private Integer checkKindId;
	private String checkKindName;
	private Integer checkItemId;
	private String checkItemName;
	private String breakDescribe;
	private String isRequiredRemarks;
	private String status;
	private String projectMode;
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	private String isNew;

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	
	public Integer getCheckItemId() {
		return checkItemId;
	}

	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}

	public String getCheckItemName() {
		return checkItemName;
	}

	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}

	public Integer getCheckKindId() {
		return checkKindId;
	}

	public void setCheckKindId(Integer checkKindId) {
		this.checkKindId = checkKindId;
	}

	public String getCheckKindName() {
		return checkKindName;
	}

	public void setCheckKindName(String checkKindName) {
		this.checkKindName = checkKindName;
	}

	public IllegalModality() {
		super();
	}

	public IllegalModality(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	
	
	@Length(min=0, max=1024, message="违规形式描述 -- '长度必须介于 0 和 1024 之间")
	public String getBreakDescribe() {
		return breakDescribe;
	}

	public void setBreakDescribe(String breakDescribe) {
		this.breakDescribe = breakDescribe;
	}
	
	@Length(min=0, max=2, message="长度必须介于 0 和 1之间")
	public String getIsRequiredRemarks() {
		return isRequiredRemarks;
	}

	public void setIsRequiredRemarks(String isRequiredRemarks) {
		this.isRequiredRemarks = isRequiredRemarks;
	}
	
	@Length(min=0, max=2, message="长度必须介于 0 和 1之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}