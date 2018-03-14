package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检报告vo
 * @author Administrator
 *
 */
public class ReportCheckDetailsPic extends DataEntity2<ReportCheckDetailsPic>{

	private static final long serialVersionUID = 1L;
	
	private Integer picId; //图片id
	private String businessType; //业务类型 '1.申请质检上传的照片；2.检查时上传的照片；3.质检验收上传的照片
	private Integer businessIdInt; //业务id整型
	private String businessIdVarchar; //业务id字符型
	private String picType; //图片类型'1.缩略图；2.大图
	private Integer picIndex; //图片序号
	private String picUrl; //图片路径
	private Date picDatetime; //图片上传日期时间
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
