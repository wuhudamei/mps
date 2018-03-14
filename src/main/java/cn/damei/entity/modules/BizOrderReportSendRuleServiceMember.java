
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizOrderReportSendRuleServiceMember extends DataEntity<BizOrderReportSendRuleServiceMember> {
	
	private static final long serialVersionUID = 4782617533753048007L;
	
	private Integer sendRuleId;
	private Integer serviceIndex;
	private Integer serviceEmployeeId;
	private String realName;
	private String phone;
	private Integer sendCount;
	private Integer sendEnd;
	private String remarks;
	
	public BizOrderReportSendRuleServiceMember() {
		super();
	}

	public BizOrderReportSendRuleServiceMember(String id){
		super(id);
	}

	public Integer getSendRuleId() {
		return sendRuleId;
	}

	public void setSendRuleId(Integer sendRuleId) {
		this.sendRuleId = sendRuleId;
	}

	public Integer getServiceIndex() {
		return serviceIndex;
	}

	public void setServiceIndex(Integer serviceIndex) {
		this.serviceIndex = serviceIndex;
	}

	public Integer getServiceEmployeeId() {
		return serviceEmployeeId;
	}

	public void setServiceEmployeeId(Integer serviceEmployeeId) {
		this.serviceEmployeeId = serviceEmployeeId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Integer getSendEnd() {
		return sendEnd;
	}

	public void setSendEnd(Integer sendEnd) {
		this.sendEnd = sendEnd;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}