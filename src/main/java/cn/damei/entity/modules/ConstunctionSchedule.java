package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class ConstunctionSchedule extends DataEntity2<ConstunctionSchedule> {

	
	
	private static final long serialVersionUID = 1L;
	
	private Integer storeId;
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	private String value;
	private String label;
	
	
	
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
