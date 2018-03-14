
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentDetailMergeTxtVo extends DataEntity2<BizOrderTaskpackagePaymentDetailMergeTxtVo> {
	
	private static final long serialVersionUID = 1L;
	private Double amount;
	private String bankCardNo;
	private String realName;
	private String bankName;
	private String bankNo;
	private String provinceName;
	
	public BizOrderTaskpackagePaymentDetailMergeTxtVo() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailMergeTxtVo(Integer id){
		super(id);
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}