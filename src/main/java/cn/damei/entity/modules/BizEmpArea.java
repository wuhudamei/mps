/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 区域信息Entity
 * @author qhy
 * @version 2016-08-24
 */
public class BizEmpArea extends DataEntity<BizEmpArea> {
	
	private static final long serialVersionUID = 1L;
	private String value;//标识
	private String label;		// 区域说明
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