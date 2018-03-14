package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class BizOrderReportRelatedOrder extends DataEntity2<BizOrderReportRelatedOrder>{
	private static final long serialVersionUID = 1L;
	
	private Integer orderReportId;
	
	private Integer orderId;
	
	private String orderNumber;
	private Date signContractDate;

	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}

	public Integer getOrderReportId() {
		return orderReportId;
	}

	public void setOrderReportId(Integer orderReportId) {
		this.orderReportId = orderReportId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
