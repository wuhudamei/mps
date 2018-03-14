
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizPhoneMsg extends DataEntity2<BizPhoneMsg> {
	
	private static final long serialVersionUID = 1L;
	private Integer receiveEmployeeId;
	private String receivePhone;
	private String msgContent;
	private Date msgGenerateDatetime;
	private Date msgTosendDatetime;
	private Date msgSendedDatetime;
	private String msgStatus;
	private String relatedBusinessType;
	private Integer relatedBusinessIdInt;
	private String relatedBusinessIdVarchar;
	
	public BizPhoneMsg() {
		super();
	}

	public BizPhoneMsg(Integer id){
		super(id);
	}

	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}

	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
	}
	
	@Length(min=0, max=11, message="短信接收手机号 --长度必须介于 0 和 11 之间")
	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsgGenerateDatetime() {
		return msgGenerateDatetime;
	}

	public void setMsgGenerateDatetime(Date msgGenerateDatetime) {
		this.msgGenerateDatetime = msgGenerateDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsgTosendDatetime() {
		return msgTosendDatetime;
	}

	public void setMsgTosendDatetime(Date msgTosendDatetime) {
		this.msgTosendDatetime = msgTosendDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsgSendedDatetime() {
		return msgSendedDatetime;
	}

	public void setMsgSendedDatetime(Date msgSendedDatetime) {
		this.msgSendedDatetime = msgSendedDatetime;
	}
	
	@Length(min=0, max=10, message="短信状态 -- '0-待发送；1-发送成功；2-发送失败长度必须介于 0 和 10 之间")
	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	
	@Length(min=0, max=10, message="关联业务类型 -- '长度必须介于 0 和 10 之间")
	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}

	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}
	
	public Integer getRelatedBusinessIdInt() {
		return relatedBusinessIdInt;
	}

	public void setRelatedBusinessIdInt(Integer relatedBusinessIdInt) {
		this.relatedBusinessIdInt = relatedBusinessIdInt;
	}
	
	@Length(min=0, max=64, message="关联业务id字符型 -- '长度必须介于 0 和 64 之间")
	public String getRelatedBusinessIdVarchar() {
		return relatedBusinessIdVarchar;
	}

	public void setRelatedBusinessIdVarchar(String relatedBusinessIdVarchar) {
		this.relatedBusinessIdVarchar = relatedBusinessIdVarchar;
	}
	
}