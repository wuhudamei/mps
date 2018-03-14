
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizQcStarCommissionLog extends DataEntity2<BizQcStarCommissionLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer qcEmployeeId;
	private Integer starLevel;
	private String commissionNode;
	private Double commissionAmount;
	private Double commissionRate;
	private Date commissionDatetime;
	
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