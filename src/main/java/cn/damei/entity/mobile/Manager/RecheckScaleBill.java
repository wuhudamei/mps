package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class RecheckScaleBill extends DataEntity2<RecheckScaleBill> {
	private Integer id;


	private Integer orderId;


	private String recheckScaleBillCode;


	private Date planInstallDate;
	

	private String type;


	private String status;


	private String remarks;


	private Date createDate;
	

	private String createPeo;
	

	private Date updateDate;
	

	private String updatePeo;
	

	private String delFlag;

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

	public String getRecheckScaleBillCode() {
		return recheckScaleBillCode;
	}

	public void setRecheckScaleBillCode(String recheckScaleBillCode) {
		this.recheckScaleBillCode = recheckScaleBillCode;
	}

	public Date getPlanInstallDate() {
		return planInstallDate;
	}

	public void setPlanInstallDate(Date planInstallDate) {
		this.planInstallDate = planInstallDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatePeo() {
		return createPeo;
	}

	public void setCreatePeo(String createPeo) {
		this.createPeo = createPeo;
	}

	public String getUpdatePeo() {
		return updatePeo;
	}

	public void setUpdatePeo(String updatePeo) {
		this.updatePeo = updatePeo;
	}

}
