package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;


public class BizPurchaseReceiveBillProduct extends DataEntity2<BizPurchaseReceiveBillProduct>{

	
	private static final long serialVersionUID = 1L;
	private Integer purchaseProductId;
	private Integer purchaseReceiveBillId;
	private Double receiveCount;
	public Integer getPurchaseProductId() {
		return purchaseProductId;
	}
	public void setPurchaseProductId(Integer purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	public Integer getPurchaseReceiveBillId() {
		return purchaseReceiveBillId;
	}
	public void setPurchaseReceiveBillId(Integer purchaseReceiveBillId) {
		this.purchaseReceiveBillId = purchaseReceiveBillId;
	}
	public Double getReceiveCount() {
		return receiveCount;
	}
	public void setReceiveCount(Double receiveCount) {
		this.receiveCount = receiveCount;
	}
	
}
