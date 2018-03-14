/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理保证金快照Entity
 * @author 汪文文
 * @version 2016-12-28
 */
public class BizPmGuaranteeMoneyCnfgSnap extends DataEntity2<BizPmGuaranteeMoneyCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private Integer orderId;		// 订单id -- '
	private Integer pmEmployeeId;		// 项目经理员工id -- '
	private String projectMode;		// 工程模式 -- '
	private Double guaranteeMoneyMax;		// 质保金上限定额 -- '
	private Double guaranteeMoneyPerOrder;		// 每个订单上缴额度 -- '
	private Date generatedDatetime;		// 生成日期时间 -- '
	
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