package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Seiralnum extends DataEntity2<Seiralnum>{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String bussinessType;
	private Integer lastSeiralnum;
	private Date generateTime;
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
