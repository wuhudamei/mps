package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class BizOrderFinishProjectBill extends DataEntity2<BizOrderFinishProjectBill> {
	private Integer id;
	
	/**
	 * 订单编号
	 */
	private Integer orderId;
	
	/**
	 * 订单竣工单编号
	 */
	private String orderFinishProjectBillCode;
	
	/**
	 * 实际竣工日期
	 */
	private Date realFinishProjectDate;
	
	/**
	 * 竣工申请人员工id
	 */
	private Integer applyEmployeeId;
	
	private String applyEmployeeName;
	
	/**
	 * 竣工申请日期时间
	 */
	private Date applyDatetime;
	
	/**
	 * 竣工审核人员工id
	 */
	private Integer checkEmployeeId;
	
	/**
	 * 审核意见 
	 */
	private String checkWords; 
	
	/**
	 * 状态
	 */
	private String status;
	
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
	 * del_flag
	 */
	private String delFlag;

	
	public String getApplyEmployeeName() {
		return applyEmployeeName;
	}

	public void setApplyEmployeeName(String applyEmployeeName) {
		this.applyEmployeeName = applyEmployeeName;
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
