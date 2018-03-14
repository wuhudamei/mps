package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;

public class SwitchPanelPic implements Serializable{


	private static final long serialVersionUID = 1L;

	
	
	private Integer picId;
	private String bussinessType;
	private  Integer relatedBussinessId;
	private String picUrl;
	private Date picUpdateTime;
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getBussinessType() {
		return bussinessType;
	}
	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}
	public Integer getRelatedBussinessId() {
		return relatedBussinessId;
	}
	public void setRelatedBussinessId(Integer relatedBussinessId) {
		this.relatedBussinessId = relatedBussinessId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Date getPicUpdateTime() {
		return picUpdateTime;
	}
	public void setPicUpdateTime(Date picUpdateTime) {
		this.picUpdateTime = picUpdateTime;
	}
	
}
