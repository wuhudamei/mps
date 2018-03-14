package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class QcBillCheckItemInfo extends DataEntity<QcBillCheckItemInfo>{
	
	private static final long serialVersionUID = 1L;
	
	private String qcBillCode;
	
	private String qcCheckKindName;
	
	private String qcCheckItemName;
	
	private Double punishMoneyAmountReal; 
	
	private Date subDate;
	
	private String pmEmpName;
	

	public String getPmEmpName() {
		return pmEmpName;
	}

	public void setPmEmpName(String pmEmpName) {
		this.pmEmpName = pmEmpName;
	}

	public String getQcBillCode() {
		return qcBillCode;
	}

	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}

	public String getQcCheckKindName() {
		return qcCheckKindName;
	}

	public void setQcCheckKindName(String qcCheckKindName) {
		this.qcCheckKindName = qcCheckKindName;
	}

	public String getQcCheckItemName() {
		return qcCheckItemName;
	}

	public void setQcCheckItemName(String qcCheckItemName) {
		this.qcCheckItemName = qcCheckItemName;
	}

	public Double getPunishMoneyAmountReal() {
		return punishMoneyAmountReal;
	}

	public void setPunishMoneyAmountReal(Double punishMoneyAmountReal) {
		this.punishMoneyAmountReal = punishMoneyAmountReal;
	}

	public Date getSubDate() {
		return subDate;
	}

	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}
	

}
