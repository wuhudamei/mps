/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;
import cn.damei.common.persistence.DataEntity2;

/**
 * 客户回访管理Entity
 * @author 王硕
 * @version 2017-08-28
 */
public class BizCustomerReturnVisitOrderEnable extends DataEntity2<BizCustomerReturnVisitOrderEnable> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;			// 订单ID
	private String returnVisitNode;		// 回访节点id
	private Integer isEnabled;		// 是否启用回访
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getReturnVisitNode() {
		return returnVisitNode;
	}
	public void setReturnVisitNode(String returnVisitNode) {
		this.returnVisitNode = returnVisitNode;
	}
	public Integer getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}