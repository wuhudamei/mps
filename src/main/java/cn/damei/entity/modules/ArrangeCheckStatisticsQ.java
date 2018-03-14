/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 约检节点验收统计Entity
 * 
 * @author 张同维
 * @version 2017-06-22
 */
public class ArrangeCheckStatisticsQ extends DataEntity<ArrangeCheckStatisticsQ> {

	private static final long serialVersionUID = 1L;
	private String qcBillCode; // 质检单编号 -- '
	private String qcBillType; // 门店
	private String isRecheck; // 是否复检 -- '0.否；1.是；默认0
	private String relatedQcBillId; // 关联质检单id -- '
	private String orderId; // 订单id -- '
	private String qcCheckNodeId; // 检查节点id -- '
	private String applyRemarks; // 申请备注 -- '
	private String status; // 状态 --
							// '复检单状态：1.创建；2.项目经理已申请；3.复检不合格；4.复检合格；约检单抽检单状态：0.暂存；2.项目经理已申请；5.已检查完成；6.已确认验收；
							// 抽检单: -1 :只签到 0:暂存 5:检查完成
	private String applyEmployeeId; // 质检申请人员工id -- '
	private String checkEmployeeId; // 实际质检人员工id -- '
	private Date checkDatetime; // 质检日期时间 -- '
	private Date expectCheckDatetime; // 期望质检日期时间 -- '
	private Date acceptCheckDatetime; // 验收日期时间 -- '
	private String acceptCheckDatetimeString; // 验收日期时间 -- '
	private String totalScore; // 总分 -- '
	private String gotScore; // 实际得分 -- '
	private String recheckTimes; // recheck_times
	private String checkWords; // check_words
	private String reviewStatus; // &Eacute;&oacute;&ordm;&Euml;&times;&acute;&Igrave;&not;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy;
									// -- '
	private String reviewRemark; // &Eacute;&oacute;&ordm;&Euml;&Ograve;&acirc;&frac14;&ucirc;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy;
									// -- '
	private Date reviewDatetime; // &Eacute;&oacute;&ordm;&Euml;&Egrave;&Otilde;&AElig;&Uacute;&Ecirc;&plusmn;&frac14;&auml;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy;
									// -- '
	private Date planCheckDatetime; // plan_check_datetime
	private String delayReasonPm; // delay_reason_pm
	private String delayReasonQc; // delay_reason_qc
	private String cotid; // 序号
	private String storeId; // 门店
	private String datetime; // 验收时间
	private String storeName; // 门店名称
	private String checknodename; // 约检节点名称
	private String industry; // 产业
	private String tradition; // 传统
	private String total; // 合计
	private String qcchecknodeindex; //
	private Date acceptCheckDatetimeStart;
	private Date acceptCheckDatetimeEnd;
	
	public Date getAcceptCheckDatetimeStart() {
		return acceptCheckDatetimeStart;
	}

	public void setAcceptCheckDatetimeStart(Date acceptCheckDatetimeStart) {
		this.acceptCheckDatetimeStart = acceptCheckDatetimeStart;
	}

	public Date getAcceptCheckDatetimeEnd() {
		return acceptCheckDatetimeEnd;
	}

	public void setAcceptCheckDatetimeEnd(Date acceptCheckDatetimeEnd) {
		this.acceptCheckDatetimeEnd = acceptCheckDatetimeEnd;
	}

	public ArrangeCheckStatisticsQ() {
		super();
	}

	public ArrangeCheckStatisticsQ(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "质检单编号 -- '长度必须介于 0 和 64 之间")
	public String getQcBillCode() {
		return qcBillCode;
	}

	public String getChecknodename() {
		return checknodename;
	}

	public void setChecknodename(String checknodename) {
		this.checknodename = checknodename;
	}

	public String getAcceptCheckDatetimeString() {
		return acceptCheckDatetimeString;
	}

	public void setAcceptCheckDatetimeString(String acceptCheckDatetimeString) {
		this.acceptCheckDatetimeString = acceptCheckDatetimeString;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}

	@Length(min = 0, max = 10, message = "门店长度必须介于 0 和 10 之间")
	public String getQcBillType() {
		return qcBillType;
	}

	public String getQcchecknodeindex() {
		return qcchecknodeindex;
	}

	public void setQcchecknodeindex(String qcchecknodeindex) {
		this.qcchecknodeindex = qcchecknodeindex;
	}

	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}

	@Length(min = 0, max = 10, message = "是；默认0长度必须介于 0 和 1 之间")
	public String getIsRecheck() {
		return isRecheck;
	}

	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}

	@Length(min = 0, max = 11, message = "关联质检单id -- '长度必须介于 0 和 11 之间")
	public String getRelatedQcBillId() {
		return relatedQcBillId;
	}

	public void setRelatedQcBillId(String relatedQcBillId) {
		this.relatedQcBillId = relatedQcBillId;
	}

	@Length(min = 0, max = 11, message = "订单id -- '长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 11, message = "检查节点id -- '长度必须介于 0 和 11 之间")
	public String getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(String qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}

	@Length(min = 0, max = 255, message = "申请备注 -- '长度必须介于 0 和 255 之间")
	public String getApplyRemarks() {
		return applyRemarks;
	}

	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}

	@Length(min = 0, max = 10, message = "已确认验收；  抽检单: -1 :只签到   0:暂存  5:检查完成长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min = 0, max = 11, message = "质检申请人员工id -- '长度必须介于 0 和 11 之间")
	public String getApplyEmployeeId() {
		return applyEmployeeId;
	}

	public void setApplyEmployeeId(String applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}

	@Length(min = 0, max = 11, message = "实际质检人员工id -- '长度必须介于 0 和 11 之间")
	public String getCheckEmployeeId() {
		return checkEmployeeId;
	}

	public String getCotid() {
		return cotid;
	}

	public void setCotid(String cotid) {
		this.cotid = cotid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getTradition() {
		return tradition;
	}

	public void setTradition(String tradition) {
		this.tradition = tradition;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setCheckEmployeeId(String checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDatetime() {
		return checkDatetime;
	}

	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}

	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}

	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getGotScore() {
		return gotScore;
	}

	public void setGotScore(String gotScore) {
		this.gotScore = gotScore;
	}

	@Length(min = 0, max = 11, message = "recheck_times长度必须介于 0 和 11 之间")
	public String getRecheckTimes() {
		return recheckTimes;
	}

	public void setRecheckTimes(String recheckTimes) {
		this.recheckTimes = recheckTimes;
	}

	@Length(min = 0, max = 255, message = "check_words长度必须介于 0 和 255 之间")
	public String getCheckWords() {
		return checkWords;
	}

	public void setCheckWords(String checkWords) {
		this.checkWords = checkWords;
	}

	@Length(min = 0, max = 10, message = "&Eacute;&oacute;&ordm;&Euml;&times;&acute;&Igrave;&not;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy; -- '长度必须介于 0 和 10 之间")
	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Length(min = 0, max = 500, message = "&Eacute;&oacute;&ordm;&Euml;&Ograve;&acirc;&frac14;&ucirc;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy; -- '长度必须介于 0 和 500 之间")
	public String getReviewRemark() {
		return reviewRemark;
	}

	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReviewDatetime() {
		return reviewDatetime;
	}

	public void setReviewDatetime(Date reviewDatetime) {
		this.reviewDatetime = reviewDatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanCheckDatetime() {
		return planCheckDatetime;
	}

	public void setPlanCheckDatetime(Date planCheckDatetime) {
		this.planCheckDatetime = planCheckDatetime;
	}

	@Length(min = 0, max = 255, message = "delay_reason_pm长度必须介于 0 和 255 之间")
	public String getDelayReasonPm() {
		return delayReasonPm;
	}

	public void setDelayReasonPm(String delayReasonPm) {
		this.delayReasonPm = delayReasonPm;
	}

	@Length(min = 0, max = 255, message = "delay_reason_qc长度必须介于 0 和 255 之间")
	public String getDelayReasonQc() {
		return delayReasonQc;
	}

	public void setDelayReasonQc(String delayReasonQc) {
		this.delayReasonQc = delayReasonQc;
	}

}