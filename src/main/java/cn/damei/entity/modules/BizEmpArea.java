
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizEmpArea extends DataEntity<BizEmpArea> {
	
	private static final long serialVersionUID = 1L;
	private String value;
	private String label;
	public BizEmpArea() {
		super();
	}
	public BizEmpArea(String value) {
		this.value = value;
	}
	public BizEmpArea(String value, String label) {
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