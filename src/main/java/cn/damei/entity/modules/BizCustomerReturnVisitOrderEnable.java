
package cn.damei.entity.modules;
import cn.damei.common.persistence.DataEntity2;


public class BizCustomerReturnVisitOrderEnable extends DataEntity2<BizCustomerReturnVisitOrderEnable> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String returnVisitNode;
	private Integer isEnabled;
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