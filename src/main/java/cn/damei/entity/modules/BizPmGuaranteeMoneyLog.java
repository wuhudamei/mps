/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质保金日志Entity
 * @author 汪文文
 * @version 2017-01-05
 */
public class BizPmGuaranteeMoneyLog extends DataEntity2<BizPmGuaranteeMoneyLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer pmEmployeeId;		// 项目经理员工id -- '
	private Integer orderId;		// 订单id -- '
	private Double takeoffAmount;		// 质保金上缴金额 -- '
	private Date takeoffDatetime;		// 质保金上缴日期时间 -- '
	private Double takeoffAmountTotal;		// 质保金累积上缴金额 -- '
	private Double guaranteeMoneyBalance;//质保金余额
	
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