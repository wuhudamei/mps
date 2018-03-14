
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePayment extends DataEntity2<BizOrderTaskpackagePayment> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackageSettlementId;
	private String orderTaskpackagePaymentCode;
	private String orderTaskpackagePaymentType;
	private Double amount;
	private String status;
	private Double paymentRates;
	private Date statusDatetime;
	private Date generatedDatetime;
	private String remarks;
	private Integer storeId;
	private Integer enginDepartId;
	private String projectMode;
	
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public BizOrderTaskpackagePayment() {
		super();
	}

	public BizOrderTaskpackagePayment(Integer id){
		super(id);
	}

	public Integer getOrderTaskpackageSettlementId() {
		return orderTaskpackageSettlementId;
	}

	public void setOrderTaskpackageSettlementId(Integer orderTaskpackageSettlementId) {
		this.orderTaskpackageSettlementId = orderTaskpackageSettlementId;
	}
	
	@Length(min=0, max=64, message="付款单编号 -- '长度必须介于 0 和 64 之间")
	public String getOrderTaskpackagePaymentCode() {
		return orderTaskpackagePaymentCode;
	}

	public void setOrderTaskpackagePaymentCode(String orderTaskpackagePaymentCode) {
		this.orderTaskpackagePaymentCode = orderTaskpackagePaymentCode;
	}
	
	@Length(min=0, max=10, message="付款单类型 -- '长度必须介于 0 和 10 之间")
	public String getOrderTaskpackagePaymentType() {
		return orderTaskpackagePaymentType;
	}

	public void setOrderTaskpackagePaymentType(String orderTaskpackagePaymentType) {
		this.orderTaskpackagePaymentType = orderTaskpackagePaymentType;
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

	public Double getPaymentRates() {
		return paymentRates;
	}

	public void setPaymentRates(Double paymentRates) {
		this.paymentRates = paymentRates;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
}