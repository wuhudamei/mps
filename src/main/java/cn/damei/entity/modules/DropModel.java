/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 页面下拉框实体
 * 
 * @author qhy
 * @version 2016-08-24
 */
public class DropModel extends DataEntity<DropModel> {

	private static final long serialVersionUID = 1L;
	
	private String label;
	private String value;
	public DropModel() {
		super();
	}
	public DropModel(String value) {
		this.value = value;
	}
	public DropModel(String label, String value) {
		this.label = label;
		this.value = value;
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