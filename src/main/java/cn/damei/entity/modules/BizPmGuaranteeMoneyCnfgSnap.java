
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizPmGuaranteeMoneyCnfgSnap extends DataEntity2<BizPmGuaranteeMoneyCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer orderId;
	private Integer pmEmployeeId;
	private String projectMode;
	private Double guaranteeMoneyMax;
	private Double guaranteeMoneyPerOrder;
	private Date generatedDatetime;
	
	public BizPmGuaranteeMoneyCnfgSnap() {
		super();
	}

	public BizPmGuaranteeMoneyCnfgSnap(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	@Length(min=0, max=10, message="工程模式 -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public Double getGuaranteeMoneyMax() {
		return guaranteeMoneyMax;
	}

	public void setGuaranteeMoneyMax(Double guaranteeMoneyMax) {
		this.guaranteeMoneyMax = guaranteeMoneyMax;
	}
	
	public Double getGuaranteeMoneyPerOrder() {
		return guaranteeMoneyPerOrder;
	}

	public void setGuaranteeMoneyPerOrder(Double guaranteeMoneyPerOrder) {
		this.guaranteeMoneyPerOrder = guaranteeMoneyPerOrder;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}
	
}