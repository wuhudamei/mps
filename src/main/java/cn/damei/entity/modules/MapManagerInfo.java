package cn.damei.entity.modules;

import java.io.Serializable;

public class MapManagerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String managerName;
	private String managerPhone;
	private String starLevel;
	private  String managerAddress;
	private Double pointX;
	private Double pointY;
	private Integer storeId;
	private Integer engineDepartId;
	private Integer projectMode;
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public Integer getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}
	public Double getPointX() {
		return pointX;
	}
	public void setPointX(Double pointX) {
		this.pointX = pointX;
	}
	public Double getPointY() {
		return pointY;
	}
	public void setPointY(Double pointY) {
		this.pointY = pointY;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	public String getManagerAddress() {
		return managerAddress;
	}
	public void setManagerAddress(String managerAddress) {
		this.managerAddress = managerAddress;
	}
	
	
}
