package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class BizOrderConfirmStartworkPic extends DataEntity2<BizOrderConfirmStartworkPic> {
	private Integer id;


	private Integer orderConfirmStartworkId;


	private String picUrl;


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

	public Integer getOrderConfirmStartworkId() {
		return orderConfirmStartworkId;
	}

	public void setOrderConfirmStartworkId(Integer orderConfirmStartworkId) {
		this.orderConfirmStartworkId = orderConfirmStartworkId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

}
