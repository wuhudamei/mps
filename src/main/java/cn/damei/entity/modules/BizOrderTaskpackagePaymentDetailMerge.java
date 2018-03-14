
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentDetailMerge extends DataEntity2<BizOrderTaskpackagePaymentDetailMerge> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackagePaymentSummaryId;
	private Integer employeeId;
	private Double amount;
	private String status;
	private Date statusDatetime;
	private Date payDatetime;
	
	public BizOrderTaskpackagePaymentDetailMerge() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailMerge(Integer id){
		super(id);
	}

	public Integer getOrderTaskpackagePaymentSummaryId() {
		return orderTaskpackagePaymentSummaryId;
	}

	public void setOrderTaskpackagePaymentSummaryId(Integer orderTaskpackagePaymentSummaryId) {
		this.orderTaskpackagePaymentSummaryId = orderTaskpackagePaymentSummaryId;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=10, message="状态 -- '长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDatetime() {
		return payDatetime;
	}

	public void setPayDatetime(Date payDatetime) {
		this.payDatetime = payDatetime;
	}
	
}