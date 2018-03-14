package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * biz_order_disclose
 * 订单交底
 * @author llp
 * 2016/10/17
 */
@SuppressWarnings("serial")
public class BizDisclose extends DataEntity2<BizDisclose> {
	private Integer id;
	/**
	 * 订单ID
	 */
	private Integer orderId;

	/**
	 * 交底人_员工id
	 */
	private Integer discloseEmployeeId;

	/**
	 * 交底日期
	 */
	private Date discloseDate;

	//页面取值用的字段
	/**
	 * 设计交底/延期天数
	 */
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
