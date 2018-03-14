package cn.damei.entity.mobile.Inspector;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joseph on 2017/4/22.
 */
@JsonIgnoreType
public class ApplyCheckDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private ApplyCheckOrderEntity orderEntity;
	private String qcCheckNodeName;
	private Date managerApplyDate;// 经理申请日期
	private Date hopePqcCheckDate;// 期望质检日期
	private Date pqcSignDate;// 质检签到日期
	private Date pqcSubmitDate;// 质检提交报告日期
	private Date pqcCheckDoneDate;// 质检确认验收日期
	private String scores;// 实际得分/总得分
	private Integer qcBillId;
	private String delayReasonPm;
	private String delayReasonQc;
	private Integer bizEvalScoreId;

	public Integer getBizEvalScoreId() {
		return bizEvalScoreId;
	}

	public void setBizEvalScoreId(Integer bizEvalScoreId) {
		this.bizEvalScoreId = bizEvalScoreId;
	}

	public String getDelayReasonPm() {
		return delayReasonPm;
	}

	public void setDelayReasonPm(String delayReasonPm) {
		this.delayReasonPm = delayReasonPm;
	}

	public String getDelayReasonQc() {
		return delayReasonQc;
	}

	public void setDelayReasonQc(String delayReasonQc) {
		this.delayReasonQc = delayReasonQc;
	}

	public Integer getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}

	public ApplyCheckOrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(ApplyCheckOrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public Date getManagerApplyDate() {
		return managerApplyDate;
	}

	public void setManagerApplyDate(Date managerApplyDate) {
		this.managerApplyDate = managerApplyDate;
	}

	public Date getHopePqcCheckDate() {
		return hopePqcCheckDate;
	}

	public void setHopePqcCheckDate(Date hopePqcCheckDate) {
		this.hopePqcCheckDate = hopePqcCheckDate;
	}

	public Date getPqcSignDate() {
		return pqcSignDate;
	}

	public void setPqcSignDate(Date pqcSignDate) {
		this.pqcSignDate = pqcSignDate;
	}

	public Date getPqcSubmitDate() {
		return pqcSubmitDate;
	}

	public void setPqcSubmitDate(Date pqcSubmitDate) {
		this.pqcSubmitDate = pqcSubmitDate;
	}

	public Date getPqcCheckDoneDate() {
		return pqcCheckDoneDate;
	}

	public void setPqcCheckDoneDate(Date pqcCheckDoneDate) {
		this.pqcCheckDoneDate = pqcCheckDoneDate;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}
}
