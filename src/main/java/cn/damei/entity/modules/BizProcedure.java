/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工序管理Entity
 * @author 魏建勇
 * @version 2016-09-03
 */
public class BizProcedure extends DataEntity<BizProcedure> {
	
	private static final long serialVersionUID = 1L;
	private String procedureNo;		// 工序编号
	private String procedureName;		// 工序名称
	private String isOtherFlag;		// 是否其他
	private String measurementUnit;		// 计量单位
	private String isEnable;		// 启用标记
	private Date beginDate;        // 开始日期
    private Date endDate;       // 结束日期
	
	public BizProcedure() {
		super();
	}

	public BizProcedure(String id){
		super(id);
	}

	@Length(min=1, max=100, message="工序编号长度必须介于 1 和 100 之间")
	public String getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}
	
	@Length(min=1, max=50, message="工序名称长度必须介于 1 和 50 之间")
	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
	@Length(min=0, max=1, message="是否其他长度必须介于 0 和 1 之间")
	public String getIsOtherFlag() {
		return isOtherFlag;
	}

	public void setIsOtherFlag(String isOtherFlag) {
		this.isOtherFlag = isOtherFlag;
	}
	
	@Length(min=1, max=25, message="计量单位长度必须介于 1 和 25 之间")
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	
	@Length(min=1, max=1, message="启用标记长度必须介于 1 和 1 之间")
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
	
}