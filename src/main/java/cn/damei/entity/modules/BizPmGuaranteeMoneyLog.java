
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizPmGuaranteeMoneyLog extends DataEntity2<BizPmGuaranteeMoneyLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer pmEmployeeId;
	private Integer orderId;
	private Double takeoffAmount;
	private Date takeoffDatetime;
	private Double takeoffAmountTotal;
	private Double guaranteeMoneyBalance;
	
	public BizPmGuaranteeMoneyLog() {
		super();
	}

	public BizPmGuaranteeMoneyLog(Integer id){
		super(id);
	}

	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Double getTakeoffAmount() {
		return takeoffAmount;
	}

	public void setTakeoffAmount(Double takeoffAmount) {
		this.takeoffAmount = takeoffAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTakeoffDatetime() {
		return takeoffDatetime;
	}

	public void setTakeoffDatetime(Date takeoffDatetime) {
		this.takeoffDatetime = takeoffDatetime;
	}
	
	public Double getTakeoffAmountTotal() {
		return takeoffAmountTotal;
	}

	public void setTakeoffAmountTotal(Double takeoffAmountTotal) {
		this.takeoffAmountTotal = takeoffAmountTotal;
	}

	public Double getGuaranteeMoneyBalance() {
		return guaranteeMoneyBalance;
	}

	public void setGuaranteeMoneyBalance(Double guaranteeMoneyBalance) {
		this.guaranteeMoneyBalance = guaranteeMoneyBalance;
	}
	
	
	
}