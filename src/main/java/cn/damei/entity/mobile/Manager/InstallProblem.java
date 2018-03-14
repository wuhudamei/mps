package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class InstallProblem extends DataEntity2<InstallProblem> {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderId;	//订单id
	private Integer orderInstallItemId;//订单安装项id
	private String installItemName;	//安装项名称
	private Integer installItemSequence;	//安装项顺序
	private Date applyIntoDate;//申请进场日期
	private String status;//状态 1.已生成计划；2.已申请计划；3.已验收

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
