package cn.damei.common.utils.phoneMessage;

import java.io.Serializable;
import java.util.Date;

public class PhoneMessageEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private  Integer  messageId;//主键
	private Integer  receiveEmployeeId;//接收人id
	private String receivePhone;//接受人手机
	private String messageContent;//短信内容
	private Date messageGenerateTime;//短信生成时间
	private Date messageToSendTime;//短信期望发送时间
	private Date messageSendedTime;//短信实际发送时间
	private String status;//短信状态
	private Integer relatedBusinessId;//关键业务id
	private String  relatedBusinessType;//关联业务类型
	private String relatedBusinessVarchar;

	public String getRelatedBusinessVarchar() {
		return relatedBusinessVarchar;
	}

	public void setRelatedBusinessVarchar(String relatedBusinessVarchar) {
		this.relatedBusinessVarchar = relatedBusinessVarchar;
	}

	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}
	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
	}
	public String getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getMessageGenerateTime() {
		return messageGenerateTime;
	}
	public void setMessageGenerateTime(Date messageGenerateTime) {
		this.messageGenerateTime = messageGenerateTime;
	}
	public Date getMessageToSendTime() {
		return messageToSendTime;
	}
	public void setMessageToSendTime(Date messageToSendTime) {
		this.messageToSendTime = messageToSendTime;
	}
	public Date getMessageSendedTime() {
		return messageSendedTime;
	}
	public void setMessageSendedTime(Date messageSendedTime) {
		this.messageSendedTime = messageSendedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}
	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}
	
	
}
