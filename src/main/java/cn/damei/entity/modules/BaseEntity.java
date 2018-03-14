/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;



public class BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String value;//标识
	private String label;		// 门店说明
	private String id;		//
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BaseEntity() {
		super();
	}
	public BaseEntity(String value) {
		this.value = value;
	}
	public BaseEntity(String value, String label) {
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