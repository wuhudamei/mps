package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务查看日志表Entity
 * @author wyb
 * @version 2016-11-16
 */
public class ViewLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id; 
	
	private String businessType;	
	private Integer businessIdInt;	
	private String businessIdVarchar;	
	private Date businessViewDatetime;	
	
	private Integer businessViewerEmployeeId;	
	private String businessViewerOnlyMark; 
	
	private String remarks; 
	private Integer createBy;	
	private Date createDate; 
	
	private Integer updateBy; 
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
	public Date getBusinessViewDatetime() {
		return businessViewDatetime;
	}
	public void setBusinessViewDatetime(Date businessViewDatetime) {
		this.businessViewDatetime = businessViewDatetime;
	}
	public Integer getBusinessViewerEmployeeId() {
		return businessViewerEmployeeId;
	}
	public void setBusinessViewerEmployeeId(Integer businessViewerEmployeeId) {
		this.businessViewerEmployeeId = businessViewerEmployeeId;
	}
	public String getBusinessViewerOnlyMark() {
		return businessViewerOnlyMark;
	}
	public void setBusinessViewerOnlyMark(String businessViewerOnlyMark) {
		this.businessViewerOnlyMark = businessViewerOnlyMark;
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
	
	
	
		
}