/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 返单客服轮训规则业务处理Entity
 * @author Liwancai
 * @version 2017-05-06
 */
public class BizOrderReportSendRule extends DataEntity<BizOrderReportSendRule> {
	
	private static final long serialVersionUID = 1L;
	private Date startDatetime;		// 开始时间
	private Date endDatetime;		// 结束时间
	private String status;		// 规则状态
	
	private String createName;	//创建人姓名
	
	public BizOrderReportSendRule() {
		super();
	}

	public BizOrderReportSendRule(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
}