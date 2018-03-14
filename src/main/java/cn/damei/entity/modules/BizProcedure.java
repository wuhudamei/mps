
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizProcedure extends DataEntity<BizProcedure> {
	
	private static final long serialVersionUID = 1L;
	private String procedureNo;
	private String procedureName;
	private String isOtherFlag;
	private String measurementUnit;
	private String isEnable;
	private Date beginDate;
    private Date endDate;
	
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


    public Date getBeginDate() {
        return beginDate;
    }


    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
	
}