package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;

public class BroadCastPicEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer orderId;
	private String broadCastName;
	private String cusBroadCastType;
	private String cusBroadCastCode;
	private Integer picCount;
	private Integer applyEmployeeId;
	private Date applyDate;
	private Integer checkEmployeeId;
	private String status;
	private Date statusTime;
	private String remarks;
	private Integer createBy;
	private Date createDate;
	private Integer updateBy;
	private Date updateDate;
	private String delFlag;
	private Integer relatedBusinessId;

	
	
	
	//pic  part
	
	private String  picType;//501 播报类型
	private Integer broadCastId;//关联播报id
	private String picUrl;
	private Date picDateTime;
	
	
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public Integer getBroadCastId() {
		return broadCastId;
	}
	public void setBroadCastId(Integer broadCastId) {
		this.broadCastId = broadCastId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Date getPicDateTime() {
		return picDateTime;
	}
	public void setPicDateTime(Date picDateTime) {
		this.picDateTime = picDateTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getBroadCastName() {
		return broadCastName;
	}
	public void setBroadCastName(String broadCastName) {
		this.broadCastName = broadCastName;
	}
	public String getCusBroadCastType() {
		return cusBroadCastType;
	}
	public void setCusBroadCastType(String cusBroadCastType) {
		this.cusBroadCastType = cusBroadCastType;
	}
	public String getCusBroadCastCode() {
		return cusBroadCastCode;
	}
	public void setCusBroadCastCode(String cusBroadCastCode) {
		this.cusBroadCastCode = cusBroadCastCode;
	}
	public Integer getPicCount() {
		return picCount;
	}
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}
	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
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
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	
	
}
