package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class InstallProblem extends DataEntity2<InstallProblem> {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderId;
	private Integer orderInstallItemId;
	private String installItemName;
	private Integer installItemSequence;
	private Date applyIntoDate;
	private String status;

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
	public Integer getOrderInstallItemId() {
		return orderInstallItemId;
	}
	public void setOrderInstallItemId(Integer orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}
	public String getInstallItemName() {
		return installItemName;
	}
	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}
	public Integer getInstallItemSequence() {
		return installItemSequence;
	}
	public void setInstallItemSequence(Integer installItemSequence) {
		this.installItemSequence = installItemSequence;
	}
	public Date getApplyIntoDate() {
		return applyIntoDate;
	}
	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	





}
