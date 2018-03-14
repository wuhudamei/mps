package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class ConstunctionSchedule extends DataEntity2<ConstunctionSchedule> {
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer storeId;//对应门店id
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	private String value;//标识
	private String label;		// 进度模板说明
	
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
