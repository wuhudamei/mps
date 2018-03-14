package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BizQcBill  extends DataEntity2<BizQcBill>{

	/**
	 * 质检端,抽检单
	 */
	private static final long serialVersionUID = 1L;

	private Integer qcBillId;	//抽检单id
	private String qcBillCode; // 抽检单编号
	private String qcBillType; // 抽检单类型 1约检单  2抽检单
	
	private String isRecheck; // 是否复检
	private Integer relatedQcBillId; // 关联抽检单id
	
	private Integer orderId; // 订单id
	private Integer qcCheckNodeId; // 检查节点id
	private String applyRemarks; // 申请备注
	
	private String status; // 状态 -- '抽检单状态：-1.未选择检查项；0.暂存；5.提交报告；
	
	
	private Integer applyEmployeeId; // 质检申请人员工id
	private Integer checkEmployeeId; // 实际质检人员工id
	
	private Date checkDatetime; // 质检日期时间
	private Date expectCheckDatetime; // 期望质检日期时间
	private Date acceptCheckDatetime; // 验收日期时间
	
	private Double totalScore; // 总分
	private Double gotScore; // 实际得分
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}
	public String getQcBillCode() {
		return qcBillCode;
	}
	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}
	public String getQcBillType() {
		return qcBillType;
	}
	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}
	public String getIsRecheck() {
		return isRecheck;
	}
	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}
	public Integer getRelatedQcBillId() {
		return relatedQcBillId;
	}
	public void setRelatedQcBillId(Integer relatedQcBillId) {
		this.relatedQcBillId = relatedQcBillId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}
	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	public String getApplyRemarks() {
		return applyRemarks;
	}
	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}
	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}
	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}
	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public Double getGotScore() {
		return gotScore;
	}
	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}
	
	
	
}
