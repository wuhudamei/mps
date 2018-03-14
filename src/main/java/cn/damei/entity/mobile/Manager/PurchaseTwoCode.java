package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月7日 下午4:31:06 
* 类说明 
*/

public class PurchaseTwoCode implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String purchaseCode;//四位流水号
	private Date auxiliaryDate;//当前日期
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
