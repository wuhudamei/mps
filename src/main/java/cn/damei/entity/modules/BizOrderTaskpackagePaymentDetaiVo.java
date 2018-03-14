
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentDetaiVo extends DataEntity2<BizOrderTaskpackagePaymentDetaiVo> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer employeeId;
	private Double allAmount;
	private Integer orderTaskpackagePaymentId;
	private Integer count;
	
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