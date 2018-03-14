package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

public class BizPrePmSettleFin extends DataEntity2<BizPrePmSettleFin> {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer orderId;

	private String orderNumber;

	private String receiveMoneyType;//1:二期款 2：尾款

	private Double receiveMoneyAmount;

	private Date receiveMoneyDatetime;
	
	private Date deptMoneyDate;//催款时间

	private String remarks;
	
	private String receiveMoneyTime;

	private String collectionStatus;//收款状态
	

	public String getCollectionStatus() {
		return collectionStatus;
	}

	public void setCollectionStatus(String collectionStatus) {
		this.collectionStatus = collectionStatus;
	}

	public Date getDeptMoneyDate() {
		return deptMoneyDate;
	}

	public void setDeptMoneyDate(Date deptMoneyDate) {
		this.deptMoneyDate = deptMoneyDate;
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

	@Length(min = 0, max = 100, message = "订单编号长度必须介于 0 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getReceiveMoneyType() {
		return receiveMoneyType;
	}

	public void setReceiveMoneyType(String receiveMoneyType) {
		this.receiveMoneyType = receiveMoneyType;
	}

	public Double getReceiveMoneyAmount() {
		return receiveMoneyAmount;
	}

	public void setReceiveMoneyAmount(Double receiveMoneyAmount) {
		this.receiveMoneyAmount = receiveMoneyAmount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveMoneyDatetime() {
		return receiveMoneyDatetime;
	}

	public void setReceiveMoneyDatetime(Date receiveMoneyDatetime) {
		this.receiveMoneyDatetime = receiveMoneyDatetime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReceiveMoneyTime() {
		return receiveMoneyTime;
	}

	public void setReceiveMoneyTime(String receiveMoneyTime) {
		this.receiveMoneyTime = receiveMoneyTime;
	}
	

}
