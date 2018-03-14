package cn.damei.entity.modules;

import java.io.Serializable;

public class BizSynDataCnfg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String businessType;
	private String businessData;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessData() {
		return businessData;
	}
	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}
	public BizSynDataCnfg(String id, String businessType, String businessData) {
		super();
		this.id = id;
		this.businessType = businessType;
		this.businessData = businessData;
	}
	public BizSynDataCnfg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
