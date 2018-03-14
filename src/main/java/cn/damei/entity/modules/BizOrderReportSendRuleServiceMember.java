/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 返单客服轮训规则客服人员Entity
 * @author Liwancai
 * @version 2017-05-06
 */
public class BizOrderReportSendRuleServiceMember extends DataEntity<BizOrderReportSendRuleServiceMember> {
	
	private static final long serialVersionUID = 4782617533753048007L;
	
	private Integer sendRuleId;			//规则ID
	private Integer serviceIndex;		//轮循顺序
	private Integer serviceEmployeeId;	//客服人员ID
	private String realName;			//客服人员真实名称
	private String phone;				//客服人员手机号
	private Integer sendCount;			//客服人员分单总数
	private Integer sendEnd;			//是否轮训结尾
	private String remarks;				//备注
	
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