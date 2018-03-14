
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentDetailMergeVo extends DataEntity2<BizOrderTaskpackagePaymentDetailMergeVo> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String realName;
	private Double amount;
	private String status;
	private String idCardNo;
	private String bankCardNo;

	private String phone;
	private Integer employeeId;
	
	public BizOrderTaskpackagePaymentDetailMergeVo() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailMergeVo(Integer id){
		super(id);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
}