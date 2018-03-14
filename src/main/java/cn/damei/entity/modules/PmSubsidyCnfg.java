package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class PmSubsidyCnfg extends DataEntity2<PmSubsidyCnfg>{


	private static final long serialVersionUID = 1L;

	
	private String storeId       ;
	private String projectMode   ;
	private Double squareMin     ;
	private Double squareMax     ;
	private String subsidyPrice  ;
	private String isEnabled     ;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public Double getSquareMin() {
		return squareMin;
	}
	public void setSquareMin(Double squareMin) {
		this.squareMin = squareMin;
	}
	public Double getSquareMax() {
		return squareMax;
	}
	public void setSquareMax(Double squareMax) {
		this.squareMax = squareMax;
	}
	public String getSubsidyPrice() {
		return subsidyPrice;
	}
	public void setSubsidyPrice(String subsidyPrice) {
		this.subsidyPrice = subsidyPrice;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
