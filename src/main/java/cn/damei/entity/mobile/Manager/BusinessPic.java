package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BusinessPic extends DataEntity2<BusinessPic> {
	private static final long serialVersionUID = 3599577989786988780L;

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 *  业务类型
	 * '1.申请质检上传的照片；
	 * 2.检查时上传的照片；
	 * 3.质检验收上传的照片；
	 */
	private String businessType;
	
	/**
	 * 业务id整型 
	 */
	private Integer businessIdInt;
	
	/**
	 * 业务id字符型
	 */
	private String businessIdVarchar;
	
	/**
	 * 图片类型
	 * 1.缩略图
	 * 2.大图
	 */
	private String picType;
	
	/**
	 * 图片序号
	 */
	private Integer picIndex;
	
	/**
	 * 图片文件路径 
	 */
	private String picUrl;
	
	/**
	 * 图片上传日期时间
	 */
	private Date picDatetime;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 创建日期时间
	 */
	private Date createDate;
	
	/**
	 * 更新日期时间
	 */
	private Date updateDate;
	
	/**
	 * 是否删除
	 */
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
