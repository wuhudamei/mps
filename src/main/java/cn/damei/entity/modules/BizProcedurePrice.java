/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 工序价格管理Entity
 * @author 魏建勇
 * @version 2016-09-03
 */
public class BizProcedurePrice extends DataEntity<BizProcedurePrice> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店id
	private String projectMode;//工程模式
	private String procedureNo;		// 描述
	private java.math.BigDecimal laborPrice;		// 人工价
	private java.math.BigDecimal accessoriesPrice;		// 计量单位
	private java.math.BigDecimal synthesizePrice;		// 综合价
	private String isEnable;		// 启用标记
	private int version;		// 版本号
	private Date effectiveDate;		// 生效日期
	
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public BizProcedurePrice() {
		super();
	}

	public BizProcedurePrice(String id){
		super(id);
	}

	@Length(min=0, max=64, message="门店id长度必须介于 0 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=100, message="描述长度必须介于 0 和 100 之间")
	public String getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}
	
	public BigDecimal getLaborPrice() {
		return laborPrice;
	}

	public void setLaborPrice(BigDecimal laborPrice) {
		this.laborPrice = laborPrice;
	}
	
	public BigDecimal getAccessoriesPrice() {
		return accessoriesPrice;
	}

	public void setAccessoriesPrice(BigDecimal accessoriesPrice) {
		this.accessoriesPrice = accessoriesPrice;
	}
	
	public BigDecimal getSynthesizePrice() {
		return synthesizePrice;
	}

	public void setSynthesizePrice(BigDecimal synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}
	
	@Length(min=1, max=1, message="启用标记长度必须介于 1 和 1 之间")
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
	//@Length(min=0, max=4, message="版本号长度必须介于 0 和 4 之间")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}