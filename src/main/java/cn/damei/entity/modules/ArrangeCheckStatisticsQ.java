
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class ArrangeCheckStatisticsQ extends DataEntity<ArrangeCheckStatisticsQ> {

	private static final long serialVersionUID = 1L;
	private String qcBillCode;
	private String qcBillType;
	private String isRecheck;
	private String relatedQcBillId;
	private String orderId;
	private String qcCheckNodeId;
	private String applyRemarks;
	private String status;


	private String applyEmployeeId;
	private String checkEmployeeId;
	private Date checkDatetime;
	private Date expectCheckDatetime;
	private Date acceptCheckDatetime;
	private String acceptCheckDatetimeString;
	private String totalScore;
	private String gotScore;
	private String recheckTimes;
	private String checkWords;
	private String reviewStatus;

	private String reviewRemark;

	private Date reviewDatetime;

	private Date planCheckDatetime;
	private String delayReasonPm;
	private String delayReasonQc;
	private String cotid;
	private String storeId;
	private String datetime;
	private String storeName;
	private String checknodename;
	private String industry;
	private String tradition;
	private String total;
	private String qcchecknodeindex;
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