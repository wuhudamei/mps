
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentDetailSplit extends DataEntity2<BizOrderTaskpackagePaymentDetailSplit> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackagePaymentDetailId;
	private Integer salaryEmployeeId;
	private Integer employeeBankcardId;
	private Integer employeeBankcardRelatedIdcardId;
	private String salaryEmployeeName;
	private String salaryEmployeeIdcardNo;
	private String salaryEmployeeBankcard;
	private Double payAmountTotal;
	private String relatedName;
	private String relatedIdcardNo;
	private Double payAmountSplit;

	public BizOrderTaskpackagePaymentDetailSplit() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailSplit(Integer id){
		super(id);
	}

	public Integer getOrderTaskpackagePaymentDetailId() {
		return orderTaskpackagePaymentDetailId;
	}

	public void setOrderTaskpackagePaymentDetailId(Integer orderTaskpackagePaymentDetailId) {
		this.orderTaskpackagePaymentDetailId = orderTaskpackagePaymentDetailId;
	}
	
	public Integer getSalaryEmployeeId() {
		return salaryEmployeeId;
	}

	public void setSalaryEmployeeId(Integer salaryEmployeeId) {
		this.salaryEmployeeId = salaryEmployeeId;
	}
	
	public Integer getEmployeeBankcardId() {
		return employeeBankcardId;
	}

	public void setEmployeeBankcardId(Integer employeeBankcardId) {
		this.employeeBankcardId = employeeBankcardId;
	}
	
	public Integer getEmployeeBankcardRelatedIdcardId() {
		return employeeBankcardRelatedIdcardId;
	}

	public void setEmployeeBankcardRelatedIdcardId(Integer employeeBankcardRelatedIdcardId) {
		this.employeeBankcardRelatedIdcardId = employeeBankcardRelatedIdcardId;
	}
	
	@Length(min=0, max=64, message="收款员工姓名 -- '长度必须介于 0 和 64 之间")
	public String getSalaryEmployeeName() {
		return salaryEmployeeName;
	}

	public void setSalaryEmployeeName(String salaryEmployeeName) {
		this.salaryEmployeeName = salaryEmployeeName;
	}
	
	@Length(min=0, max=64, message="收款员工身份证号 -- '长度必须介于 0 和 64 之间")
	public String getSalaryEmployeeIdcardNo() {
		return salaryEmployeeIdcardNo;
	}

	public void setSalaryEmployeeIdcardNo(String salaryEmployeeIdcardNo) {
		this.salaryEmployeeIdcardNo = salaryEmployeeIdcardNo;
	}
	
	@Length(min=0, max=64, message="收款员工银行卡号 -- '长度必须介于 0 和 64 之间")
	public String getSalaryEmployeeBankcard() {
		return salaryEmployeeBankcard;
	}

	public void setSalaryEmployeeBankcard(String salaryEmployeeBankcard) {
		this.salaryEmployeeBankcard = salaryEmployeeBankcard;
	}
	
	public void setPayAmountSplit(double payAmountSplit) {
		this.payAmountSplit = payAmountSplit;
	}

	@Length(min=0, max=64, message="关联人姓名 -- '长度必须介于 0 和 64 之间")
	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	
	@Length(min=0, max=64, message="关联人身份证号 -- '长度必须介于 0 和 64 之间")
	public String getRelatedIdcardNo() {
		return relatedIdcardNo;
	}

	public void setRelatedIdcardNo(String relatedIdcardNo) {
		this.relatedIdcardNo = relatedIdcardNo;
	}

	public Double getPayAmountTotal() {
		return payAmountTotal;
	}

	public void setPayAmountTotal(Double payAmountTotal) {
		this.payAmountTotal = payAmountTotal;
	}

	public Double getPayAmountSplit() {
		return payAmountSplit;
	}

	public void setPayAmountSplit(Double payAmountSplit) {
		this.payAmountSplit = payAmountSplit;
	}
}