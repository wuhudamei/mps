
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizCustomerReturnVisitInvalidRecord extends DataEntity2<BizCustomerReturnVisitInvalidRecord> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String customServiceId;
	private String customServiceName;
	private Date returnVisitTime;
	private String returnVisitNode;
	private String returnVisitNodeName;
	private String invalidReason;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomServiceId() {
		return customServiceId;
	}
	public void setCustomServiceId(String customServiceId) {
		this.customServiceId = customServiceId;
	}
	public String getCustomServiceName() {
		return customServiceName;
	}
	public void setCustomServiceName(String customServiceName) {
		this.customServiceName = customServiceName;
	}
	public Date getReturnVisitTime() {
		return returnVisitTime;
	}
	public void setReturnVisitTime(Date returnVisitTime) {
		this.returnVisitTime = returnVisitTime;
	}
	public String getReturnVisitNode() {
		return returnVisitNode;
	}
	public void setReturnVisitNode(String returnVisitNode) {
		this.returnVisitNode = returnVisitNode;
	}
	public String getReturnVisitNodeName() {
		return returnVisitNodeName;
	}
	public void setReturnVisitNodeName(String returnVisitNodeName) {
		this.returnVisitNodeName = returnVisitNodeName;
	}
	public String getInvalidReason() {
		return invalidReason;
	}
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}
	
}