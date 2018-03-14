package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;



public class PurchaseTwoCode implements Serializable {

	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String purchaseCode;
	private Date auxiliaryDate;
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public Date getAuxiliaryDate() {
		return auxiliaryDate;
	}
	public void setAuxiliaryDate(Date auxiliaryDate) {
		this.auxiliaryDate = auxiliaryDate;
	}
	
	
}
