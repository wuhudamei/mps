package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderConfirmStartworkPic extends DataEntity2<OrderConfirmStartworkPic> {
	private Integer id;

	/**
	 * 订单确认开工id
	 */
	private Integer orderConfirmStartworkId;

	/**
	 * 图片路径
	 */
	private String picUrl;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建人员工id
	 */
//	private String createBy;

	/**
	 * 创建日期时间
	 */
	private Date createDate;

	/**
	 * 更新人员工id
	 */
	/*private String updateBy;*/

	/**
	 * 更新日期时间
	 */
	private Date updateDate;

	/**
	 * 删除标识
	 */
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
