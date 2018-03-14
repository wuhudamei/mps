/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 消息推送Entity
 * @author wyb
 * @version 2016-11-16
 */
public class BizMsg extends DataEntity<BizMsg> {
	
	private static final long serialVersionUID = 1L;
	private Integer msgTd;		//id
	private String msgTitle;		//消息标题
	private Date msgTime;		// 消息时间
	private String msgContent;		//消息内容
	private String msgType;		//消息类型
	private String busiType;		//业务类型
	private String busiId;		//业务id
	private Integer busiIdInt;		//业务id
	private Integer employeeId;		//用户id
	
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