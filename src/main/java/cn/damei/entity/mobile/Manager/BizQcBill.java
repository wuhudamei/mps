package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BizQcBill extends DataEntity2<BizQcBill>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String qcBillCode; // 质检单编号
	private String qcBillType; // 质检单类型 1约检单  2抽检单
	
	private String isRecheck; // 是否复检
	private Integer relatedQcBillId; // 关联质检单id
	
	private Integer orderId; // 订单id
	private Integer qcCheckNodeId; // 检查节点id
	private String qcCheckNodeName;	//节点名称
	private Integer qcCheckNodeIndex;//节点顺序
	private String applyRemarks; // 申请备注
	
	private String status; // 状态  1创建  2项目经理已申请 3.复检不合格；4.复检合格
	
	private Integer applyEmployeeId; // 质检申请人员工id
	private Integer checkEmployeeId; // 实际质检人员工id
	
	private Date checkDatetime; // 质检日期时间
	private Date expectCheckDatetime; // 期望质检日期时间
	private Date acceptCheckDatetime; // 验收日期时间

	private String preStatusName;
	private String preCheckManName;
	private String preCheckManPhone;
	private String  statusName;
	private String checkManName;
	private String checkManPhone;
	private Date planCheckDate;
	private String delayReasonPm;
	private Integer picCount;

	public Date getPlanCheckDate() {
		return planCheckDate;
	}

	public void setPlanCheckDate(Date planCheckDate) {
		this.planCheckDate = planCheckDate;
	}

	public String getDelayReasonPm() {
		return delayReasonPm;
	}

	public void setDelayReasonPm(String delayReasonPm) {
		this.delayReasonPm = delayReasonPm;
	}

	public String getPreStatusName() {
		return preStatusName;
	}

	public void setPreStatusName(String preStatusName) {
		this.preStatusName = preStatusName;
	}

	public String getPreCheckManName() {
		return preCheckManName;
	}

	public void setPreCheckManName(String preCheckManName) {
		this.preCheckManName = preCheckManName;
	}

	public String getPreCheckManPhone() {
		return preCheckManPhone;
	}

	public void setPreCheckManPhone(String preCheckManPhone) {
		this.preCheckManPhone = preCheckManPhone;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCheckManName() {
		return checkManName;
	}

	public void setCheckManName(String checkManName) {
		this.checkManName = checkManName;
	}

	public String getCheckManPhone() {
		return checkManPhone;
	}

	public void setCheckManPhone(String checkManPhone) {
		this.checkManPhone = checkManPhone;
	}

	private Double totalScore; // 总分
	private Double gotScore; // 实际得分
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getQcCheckNodeIndex() {
		return qcCheckNodeIndex;
	}
	public void setQcCheckNodeIndex(Integer qcCheckNodeIndex) {
		this.qcCheckNodeIndex = qcCheckNodeIndex;
	}
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}
	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	
	
	
}
