package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class MessageRecord extends DataEntity2<MessageRecord>{
	
	
	/**
	 * @author wangwenwen 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer msgRecordId;
	private Integer msgId;
	private Integer employeeId;
	private Date recordTime;
	
	public Integer getMsgRecordId() {
		return msgRecordId;
	}
	public void setMsgRecordId(Integer msgRecordId) {
		this.msgRecordId = msgRecordId;
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
}
