package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class MaterialMappingRelation extends DataEntity<MaterialMappingRelation>{
	
	private String storeId;
	private String categoryOne;
	private String categoryOneName;
	private String categoryTwo;
	private String categoryTwoName;
	private String installItemId;
	private String installItemName;
	private String userName;
	private Date mtime;
	private String id;
	private String projectMode;
	private String storeName;
	private String projectModeName;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryOneName() {
		return categoryOneName;
	}
	public void setCategoryOneName(String categoryOneName) {
		this.categoryOneName = categoryOneName;
	}
	public String getCategoryTwoName() {
		return categoryTwoName;
	}
	public void setCategoryTwoName(String categoryTwoName) {
		this.categoryTwoName = categoryTwoName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	public String getCategoryOne() {
		return categoryOne;
	}
	public void setCategoryOne(String categoryOne) {
		this.categoryOne = categoryOne;
	}
	public String getCategoryTwo() {
		return categoryTwo;
	}
	public void setCategoryTwo(String categoryTwo) {
		this.categoryTwo = categoryTwo;
	}
	public String getInstallItemId() {
		return installItemId;
	}
	public void setInstallItemId(String installItemId) {
		this.installItemId = installItemId;
	}

	public String getInstallItemName() {
		return installItemName;
	}
	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}
	
}
