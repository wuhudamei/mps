
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class BizMsg extends DataEntity<BizMsg> {
	
	private static final long serialVersionUID = 1L;
	private Integer msgTd;
	private String msgTitle;
	private Date msgTime;
	private String msgContent;
	private String msgType;
	private String busiType;
	private String busiId;
	private Integer busiIdInt;
	private Integer employeeId;
	
	public Integer getMsgTd() {
		return msgTd;
	}
	public void setMsgTd(Integer msgTd) {
		this.msgTd = msgTd;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getBusiId() {
		return busiId;
	}
	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}
	public Integer getBusiIdInt() {
		return busiIdInt;
	}
	public void setBusiIdInt(Integer busiIdInt) {
		this.busiIdInt = busiIdInt;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	
}