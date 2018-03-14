package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderFinishProjectBill extends DataEntity2<OrderFinishProjectBill> {
	private Integer id;
	

	private Integer orderId;
	

	private String orderFinishProjectBillCode;
	

	private Date realFinishProjectDate;
	

	private Integer applyEmployeeId;
	

	private Date applyDatetime;
	

	private Integer checkEmployeeId;
	

	private String checkWords; 
	

	private String status;
	

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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderFinishProjectBillCode() {
		return orderFinishProjectBillCode;
	}

	public void setOrderFinishProjectBillCode(String orderFinishProjectBillCode) {
		this.orderFinishProjectBillCode = orderFinishProjectBillCode;
	}

	public Date getRealFinishProjectDate() {
		return realFinishProjectDate;
	}

	public void setRealFinishProjectDate(Date realFinishProjectDate) {
		this.realFinishProjectDate = realFinishProjectDate;
	}

	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}

	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}

	public Date getApplyDatetime() {
		return applyDatetime;
	}

	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}

	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}

	public String getCheckWords() {
		return checkWords;
	}

	public void setCheckWords(String checkWords) {
		this.checkWords = checkWords;
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
}
