
package cn.damei.entity.modules;


import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizQcLongwayCommissionLog extends DataEntity2<BizQcLongwayCommissionLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer longwayCommissionEmployeeId;
	private Integer starLevel;
	private String commissionNode;
	private Double commissionAmount;
	private Double commissionRate;
	private Date commissionDatetime;
	private String longwayCommissionType;
	
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