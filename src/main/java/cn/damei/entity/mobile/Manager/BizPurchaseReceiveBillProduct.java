package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 收货单商品entity
 * @author wang
 *
 */
public class BizPurchaseReceiveBillProduct extends DataEntity2<BizPurchaseReceiveBillProduct>{

	
	private static final long serialVersionUID = 1L;
	private Integer purchaseProductId; //材料采购单id
	private Integer purchaseReceiveBillId; //收货单id
	private Double receiveCount; //收货数量
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
