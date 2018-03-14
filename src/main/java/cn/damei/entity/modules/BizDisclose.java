package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class BizDisclose extends DataEntity2<BizDisclose> {
	private Integer id;

	private Integer orderId;


	private Integer discloseEmployeeId;


	private Date discloseDate;



	private String days;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDiscloseEmployeeId() {
		return discloseEmployeeId;
	}

	public void setDiscloseEmployeeId(Integer discloseEmployeeId) {
		this.discloseEmployeeId = discloseEmployeeId;
	}

	public Date getDiscloseDate() {
		return discloseDate;
	}

	public void setDiscloseDate(Date discloseDate) {
		this.discloseDate = discloseDate;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

}
