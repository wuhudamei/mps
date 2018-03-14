package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BusinessPic extends DataEntity2<BusinessPic> {
	private static final long serialVersionUID = 3599577989786988780L;


	private Integer id;
	

	private String businessType;
	

	private Integer businessIdInt;
	

	private String businessIdVarchar;
	

	private String picType;
	

	private Integer picIndex;
	

	private String picUrl;
	

	private Date picDatetime;
	

	private String remarks;
	

	private Date createDate;
	

	private Date updateDate;
	

	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
