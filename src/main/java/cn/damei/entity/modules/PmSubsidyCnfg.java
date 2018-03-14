package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class PmSubsidyCnfg extends DataEntity2<PmSubsidyCnfg>{

	/**
	 * 结算补助设置实体
	 */
	private static final long serialVersionUID = 1L;

	
	private String storeId       ;
	private String projectMode   ;
	private Double squareMin     ;//面积开始
	private Double squareMax     ;//面积结束
	private String subsidyPrice  ;//补助单价
	private String isEnabled     ;//是否启用
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
