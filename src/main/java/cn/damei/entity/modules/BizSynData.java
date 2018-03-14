/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 同步数据Entity
 * @author 汪文
 * @version 2017-03-15
 */
public class BizSynData extends DataEntity2<BizSynData> {
	
	private static final long serialVersionUID = 1L;
	private String dataDirection;		// 
	private String businessType;		// 
	private Integer businessOnlyMarkInt;		// 
	private String businessOnlyMarkVarchar;		// 
	private String businessData;		//
	private String synStatus;		// 
	private Date synDatetime;		//
	private String isAutoSyn;		//
	
	public BizSynData() {
		super();
	}

	public BizSynData(Integer id){
		super(id);
	}

	public String getDataDirection() {
		return dataDirection;
	}

	public void setDataDirection(String dataDirection) {
		this.dataDirection = dataDirection;
	}
	
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}
	
	public String getBusinessOnlyMarkVarchar() {
		return businessOnlyMarkVarchar;
	}

	public void setBusinessOnlyMarkVarchar(String businessOnlyMarkVarchar) {
		this.businessOnlyMarkVarchar = businessOnlyMarkVarchar;
	}
	
	public String getBusinessData() {
		return businessData;
	}

	public void setBusinessData(String businessData) {
		this.businessData = "["+businessData+"]";
	}
	
	public String getSynStatus() {
		return synStatus;
	}

	public void setSynStatus(String synStatus) {
		this.synStatus = synStatus;
	}
	
	public Date getSynDatetime() {
		return synDatetime;
	}

	public void setSynDatetime(Date synDatetime) {
		this.synDatetime = synDatetime;
	}
	
	public String getIsAutoSyn() {
		return isAutoSyn;
	}

	public void setIsAutoSyn(String isAutoSyn) {
		this.isAutoSyn = isAutoSyn;
	}
	
}