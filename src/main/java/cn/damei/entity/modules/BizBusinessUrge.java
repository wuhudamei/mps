
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizBusinessUrge extends DataEntity2<BizBusinessUrge> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessOnlyMarkInt;
	private String businessOnlyMarkVarchar;
	private String businesType;
	private String businesTypeName;
	private String operateType;
	private String operateTypeName;
	private String operateContent;
	private Integer operatorEmployeeId;
	private String operatorEmployeeName;
	private String operatorType;
	private String operatorTypeName;
	private Date operateDatetime;
	private String createId;
	
	
	
	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public BizBusinessUrge() {
		super();
	}

	public BizBusinessUrge(Integer id){
		super(id);
	}

	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}
	
	@Length(min=0, max=100, message="业务唯一标识字符型长度必须介于 0 和 100 之间")
	public String getBusinessOnlyMarkVarchar() {
		return businessOnlyMarkVarchar;
	}

	public void setBusinessOnlyMarkVarchar(String businessOnlyMarkVarchar) {
		this.businessOnlyMarkVarchar = businessOnlyMarkVarchar;
	}
	
	@Length(min=0, max=10, message="业务类型长度必须介于 0 和 10 之间")
	public String getBusinesType() {
		return businesType;
	}

	public void setBusinesType(String businesType) {
		this.businesType = businesType;
	}
	
	@Length(min=0, max=10, message="操作类型长度必须介于 0 和 10 之间")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	@Length(min=0, max=255, message="操作内容长度必须介于 0 和 255 之间")
	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	
	public Integer getOperatorEmployeeId() {
		return operatorEmployeeId;
	}

	public void setOperatorEmployeeId(Integer operatorEmployeeId) {
		this.operatorEmployeeId = operatorEmployeeId;
	}
	
	@Length(min=0, max=10, message="操作人类型长度必须介于 0 和 10 之间")
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
	public Date getOperateDatetime() {
		return operateDatetime;
	}

	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}

	public String getBusinesTypeName() {
		return businesTypeName;
	}

	public void setBusinesTypeName(String businesTypeName) {
		this.businesTypeName = businesTypeName;
	}

	public String getOperateTypeName() {
		return operateTypeName;
	}

	public void setOperateTypeName(String operateTypeName) {
		this.operateTypeName = operateTypeName;
	}

	public String getOperatorEmployeeName() {
		return operatorEmployeeName;
	}

	public void setOperatorEmployeeName(String operatorEmployeeName) {
		this.operatorEmployeeName = operatorEmployeeName;
	}

	public String getOperatorTypeName() {
		return operatorTypeName;
	}

	public void setOperatorTypeName(String operatorTypeName) {
		this.operatorTypeName = operatorTypeName;
	}
	
}