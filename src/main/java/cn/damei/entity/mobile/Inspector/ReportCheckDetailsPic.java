package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class ReportCheckDetailsPic extends DataEntity2<ReportCheckDetailsPic>{

	private static final long serialVersionUID = 1L;
	
	private Integer picId;
	private String businessType;
	private Integer businessIdInt;
	private String businessIdVarchar;
	private String picType;
	private Integer picIndex;
	private String picUrl;
	private Date picDatetime;
	
	private	List<String> picList = null;
	
	
	
	public List<String> getPicList() {
		return picList;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getBusinessIdInt() {
		return businessIdInt;
	}
	public void setBusinessIdInt(Integer businessIdInt) {
		this.businessIdInt = businessIdInt;
	}
	public String getBusinessIdVarchar() {
		return businessIdVarchar;
	}
	public void setBusinessIdVarchar(String businessIdVarchar) {
		this.businessIdVarchar = businessIdVarchar;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public Integer getPicIndex() {
		return picIndex;
	}
	public void setPicIndex(Integer picIndex) {
		this.picIndex = picIndex;
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
	
	 
	 
	 
	
	
	
}
