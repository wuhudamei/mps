/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检员星级提成记录Entity
 * @author 汪文文
 * @version 2017-02-13
 */
public class BizQcStarCommissionLog extends DataEntity2<BizQcStarCommissionLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		//订单id
	private Integer qcEmployeeId;		// 质检员id
	private Integer starLevel;		// 星级
	private String commissionNode;		// 节点
	private Double commissionAmount;		// 提成金额
	private Double commissionRate;		// 提成比例
	private Date commissionDatetime;		// 生成提成时间
	
	public BizQcStarCommissionLog() {
		super();
	}

	public BizQcStarCommissionLog(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getQcEmployeeId() {
		return qcEmployeeId;
	}

	public void setQcEmployeeId(Integer qcEmployeeId) {
		this.qcEmployeeId = qcEmployeeId;
	}
	
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	
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