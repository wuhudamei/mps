package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class BusinessPic extends DataEntity<BusinessPic>{


	private static final long serialVersionUID = 1L;

	private String businessType;
	private String businessIdInt;
	private String picUrl;
	private Date picDatetime;
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessIdInt() {
		return businessIdInt;
	}
	public void setBusinessIdInt(String businessIdInt) {
		this.businessIdInt = businessIdInt;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Date getPicDatetime() {
		return picDatetime;
	}
	public void setPicDatetime(Date picDatetime) {
		this.picDatetime = picDatetime;
	}
	public BusinessPic() {
		super();
	}
	public BusinessPic(String businessType, String businessIdInt, String picUrl, Date picDatetime) {
		super();
		this.businessType = businessType;
		this.businessIdInt = businessIdInt;
		this.picUrl = picUrl;
		this.picDatetime = picDatetime;
	}
	
	
	
	
	
}
