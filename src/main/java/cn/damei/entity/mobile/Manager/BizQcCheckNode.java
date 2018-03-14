package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BizQcCheckNode extends DataEntity2<BizQcCheckNode>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderId; //订单id
	private Integer storeId;		// 门店id -- '
	private String qcCheckNodeName;		// 节点名称 -- '
	private Integer qcCheckNodeIndex;		// 节点顺序 -- '
	private Integer daysToCheck;		// 开工后第几天检查 -- '
	private Integer constructionScheduleId;		// 所属进度节点模板id -- '

	private String isUrgePay;		// 是否缴催 -- '0.否；1.是
	private String status;		// 状态 -- '0.停用；1.启用
	private Date statusDatetime;		// 状态产生日期时间 --
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
