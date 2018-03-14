package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class NodePlanPic extends DataEntity<NodePlanPic>{
	private static final long serialVersionUID = 1L;
	
	private Integer nodePlanId;
	private String picUrl;
	private Date createDate;
	private Date updateDate;
	private String delFlag;

	public Integer getNodePlanId() {
		return nodePlanId;
	}
	public void setNodePlanId(Integer nodePlanId) {
		this.nodePlanId = nodePlanId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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
}
