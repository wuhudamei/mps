package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;
/**
 * 收货单entity
 * @author wang
 *
 */
public class BizPurchaseReceiveBill extends DataEntity2<BizPurchaseReceiveBill>{

	private static final long serialVersionUID = 1L;
	private Integer purchaseId;
	private String purchaseReceiveCode;
	private Date receiveDate;
	private Integer receiveEmployeeId;
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseReceiveCode() {
		return purchaseReceiveCode;
	}
	public void setPurchaseReceiveCode(String purchaseReceiveCode) {
		this.purchaseReceiveCode = purchaseReceiveCode;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}
	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
	}
}
