/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检员远程费记录Entity
 * @author 汪文文
 * @version 2017-02-13
 */
public class BizQcLongwayCommissionLog extends DataEntity2<BizQcLongwayCommissionLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private Integer longwayCommissionEmployeeId;		// 远程费员工id
	private Integer starLevel;		// 星级
	private String commissionNode;		// 节点
	private Double commissionAmount;		// 远程费
	private Double commissionRate;		// 比例
	private Date commissionDatetime;		// 提成时间
	private String longwayCommissionType;//远程费类型 10：准产业项目经理竣工远程费  20：质检员远程费
	
	public BizQcLongwayCommissionLog() {
		super();
	}

	public BizQcLongwayCommissionLog(Integer id){
		super(id);
	}

	public String getLongwayCommissionType() {
		return longwayCommissionType;
	}

	public void setLongwayCommissionType(String longwayCommissionType) {
		this.longwayCommissionType = longwayCommissionType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
	public Integer getLongwayCommissionEmployeeId() {
		return longwayCommissionEmployeeId;
	}

	public void setLongwayCommissionEmployeeId(Integer longwayCommissionEmployeeId) {
		this.longwayCommissionEmployeeId = longwayCommissionEmployeeId;
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
	
	public Date getCommissionDatetime() {
		return commissionDatetime;
	}

	public void setCommissionDatetime(Date commissionDatetime) {
		this.commissionDatetime = commissionDatetime;
	}
	
}