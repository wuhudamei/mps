/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 客户回访记录Entity
 * @author 李万财
 * @version 2017-06-27
 */
public class BizCustomerReturnVisitInvalidRecord extends DataEntity2<BizCustomerReturnVisitInvalidRecord> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;			// 订单ID
	private String customServiceId;		// 回访人员ID
	private String customServiceName;	// 回访人员姓名
	private Date returnVisitTime;		// 回访时间
	private String returnVisitNode;		// 回访节点id
	private String returnVisitNodeName;	// 回访节点名称
	private String invalidReason; 		// 无效原因
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