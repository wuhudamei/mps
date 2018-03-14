package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizManagerStar extends DataEntity2<BizManagerStar>{

	private static final long serialVersionUID = 8178448147379987199L;

	private Integer storeId;

	private String projectMode;

	private String starType;

	private Integer starLevel;

	private String status;
	
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
	public String getStarType() {
		return starType;
	}
	public void setStarType(String starType) {
		this.starType = starType;
	}
	public Integer getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
