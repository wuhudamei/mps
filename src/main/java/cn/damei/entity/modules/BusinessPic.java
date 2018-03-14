package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class BusinessPic extends DataEntity<BusinessPic>{

	/**
	 * 工地设计问题图片
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String businessType; //图片业务类型
	private String businessIdInt; //业务ID int型
	private String picUrl;	//图片文件路径
	private Date picDatetime;	//图片上传日期时间
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
