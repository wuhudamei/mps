/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 编号序列管理Entity
 * @author 魏建勇
 * @version 2016-08-21
 */
public class BizSeiralnum extends DataEntity2<BizSeiralnum> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id; // id
	private String bussinessType; // 业务类型 
	private Integer lastSeiralnum; // 上个流水号
	private Date generateTime; // 生成时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBussinessType() {
		return bussinessType;
	}
	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}
	public Integer getLastSeiralnum() {
		return lastSeiralnum;
	}
	public void setLastSeiralnum(Integer lastSeiralnum) {
		this.lastSeiralnum = lastSeiralnum;
	}
	public Date getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}
}