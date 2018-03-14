package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BizQcCheckNode extends DataEntity2<BizQcCheckNode>{
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderId;
	private Integer storeId;
	private String qcCheckNodeName;
	private Integer qcCheckNodeIndex;
	private Integer daysToCheck;
	private Integer constructionScheduleId;

	private String isUrgePay;
	private String status;
	private Date statusDatetime;
	private Date planCheckDate;

	public Date getPlanCheckDate() {
		return planCheckDate;
	}

	public void setPlanCheckDate(Date planCheckDate) {
		this.planCheckDate = planCheckDate;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}
	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
	public Integer getQcCheckNodeIndex() {
		return qcCheckNodeIndex;
	}
	public void setQcCheckNodeIndex(Integer qcCheckNodeIndex) {
		this.qcCheckNodeIndex = qcCheckNodeIndex;
	}
	public Integer getDaysToCheck() {
		return daysToCheck;
	}
	public void setDaysToCheck(Integer daysToCheck) {
		this.daysToCheck = daysToCheck;
	}
	public Integer getConstructionScheduleId() {
		return constructionScheduleId;
	}
	public void setConstructionScheduleId(Integer constructionScheduleId) {
		this.constructionScheduleId = constructionScheduleId;
	}
	public String getIsUrgePay() {
		return isUrgePay;
	}
	public void setIsUrgePay(String isUrgePay) {
		this.isUrgePay = isUrgePay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusDatetime() {
		return statusDatetime;
	}
	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	
	
}
