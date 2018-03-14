
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class BizOrderComplaintProblemDeal extends DataEntity<BizOrderComplaintProblemDeal> {

	private static final long serialVersionUID = 1L;
	private String orderComplaintProblemId;
	private String dealPersonType;
	private String dealEmployeeId;
	private String dealStatus;
	private Date dealStatusDatetime;
	private String dealDescribe;
	private Integer handleId;

	private String replyName;
	private Date replyDate;
	private String workerName;
	private Date workerDate;

	private String replyContent;
	private Integer overDays;
	private String isdatefla;
private Double msgsndPeriod;

	public Integer getOverDays() {
		return overDays;
	}

	public void setOverDays(Integer overDays) {
		this.overDays = overDays;
	}

	public Double getMsgsndPeriod() {
		return msgsndPeriod;
	}

	public void setMsgsndPeriod(Double msgsndPeriod) {
		this.msgsndPeriod = msgsndPeriod;
	}

	public Integer getHandleId() {


		return handleId;
	}


	public String getIsdatefla() {
		return isdatefla;
	}

	public void setIsdatefla(String isdatefla) {
		this.isdatefla = isdatefla;
	}

	public void setHandleId(Integer handleId) {
		this.handleId = handleId;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Date getWorkerDate() {
		return workerDate;
	}

	public void setWorkerDate(Date workerDate) {
		this.workerDate = workerDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public BizOrderComplaintProblemDeal() {
		super();
	}

	public BizOrderComplaintProblemDeal(String id) {
		super(id);
	}

	@Length(min = 0, max = 11, message = "问题表ID长度必须介于 0 和 11 之间")
	public String getOrderComplaintProblemId() {
		return orderComplaintProblemId;
	}

	public void setOrderComplaintProblemId(String orderComplaintProblemId) {
		this.orderComplaintProblemId = orderComplaintProblemId;
	}

	@Length(min = 0, max = 10, message = "deal_person_type长度必须介于 0 和 10 之间")
	public String getDealPersonType() {
		return dealPersonType;
	}

	public void setDealPersonType(String dealPersonType) {
		this.dealPersonType = dealPersonType;
	}

	@Length(min = 0, max = 11, message = "deal_employee_id长度必须介于 0 和 11 之间")
	public String getDealEmployeeId() {
		return dealEmployeeId;
	}

	public void setDealEmployeeId(String dealEmployeeId) {
		this.dealEmployeeId = dealEmployeeId;
	}

	@Length(min = 0, max = 10, message = "deal_status长度必须介于 0 和 10 之间")
	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDealStatusDatetime() {
		return dealStatusDatetime;
	}

	public void setDealStatusDatetime(Date dealStatusDatetime) {
		this.dealStatusDatetime = dealStatusDatetime;
	}

	@Length(min = 0, max = 255, message = "deal_describe长度必须介于 0 和 255 之间")
	public String getDealDescribe() {
		return dealDescribe;
	}

	public void setDealDescribe(String dealDescribe) {
		this.dealDescribe = dealDescribe;
	}

}