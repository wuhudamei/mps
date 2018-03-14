
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizEmpStore extends DataEntity<BizEmpStore> {
	
	private static final long serialVersionUID = 1L;
	private String value;
	private String label;
	public BizEmpStore() {
		super();
	}
	public BizEmpStore(String value) {
		this.value = value;
	}
	public BizEmpStore(String value, String label) {
		this.value = value;
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public String getValue() {
		return value;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}