/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单明细Entity
 * @author qww
 * @version 2016-10-26
 */
public class BizOrderTaskpackagePaymentDetaiVo extends DataEntity2<BizOrderTaskpackagePaymentDetaiVo> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer employeeId; // 员工id
	private Double allAmount; // 单个批次下单员工总金额
	private Integer orderTaskpackagePaymentId; // 付款单id
	private Integer count; // 数量
	
	public BizOrderTaskpackagePaymentDetaiVo() {
		super();
	}

	public BizOrderTaskpackagePaymentDetaiVo(Integer id){
		super(id);
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Double getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	public Integer getOrderTaskpackagePaymentId() {
		return orderTaskpackagePaymentId;
	}

	public void setOrderTaskpackagePaymentId(Integer orderTaskpackagePaymentId) {
		this.orderTaskpackagePaymentId = orderTaskpackagePaymentId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}