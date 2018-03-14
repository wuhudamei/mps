/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * biz_pm_star_commission_logEntity
 * @author 汪文文
 * @version 2017-02-14
 */
public class BizPmStarCommissionLog extends DataEntity2<BizPmStarCommissionLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// order_id
	private Integer pmEmployeeId;		// pm_employee_id
	private Integer starLevel;		// star_level
	private String commissionNode;		// commission_node
	private Double commissionAmount;		// commission_amount
	private Double commissionRate;		// commission_rate
	private Date commissionDatetime;		// commission_datetime
	
	public BizPmStarCommissionLog() {
		super();
	}

	public BizPmStarCommissionLog(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	
	@Length(min=0, max=10, message="commission_node长度必须介于 0 和 10 之间")
	public String getCommissionNode() {
		return commissionNode;
	}

	public void setCommissionNode(String commissionNode) {
		this.commissionNode = commissionNode;
	}
	
	public Double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCommissionDatetime() {
		return commissionDatetime;
	}

	public void setCommissionDatetime(Date commissionDatetime) {
		this.commissionDatetime = commissionDatetime;
	}
	
}