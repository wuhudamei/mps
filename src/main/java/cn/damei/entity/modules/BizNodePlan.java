/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 进度节点Entity
 * @author llp
 * @version 2016-10-10
 */
public class BizNodePlan extends DataEntity2<BizNodePlan> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;		// 订单id -- '
	private String nodeName;		// 节点名称 -- '
	private String nodeIndex;		// 节点序号 -- '
	private Date planDoneDate;		// 计划完成日期 -- '
	private Date exeDoneDate;		// 执行完成日期 -- '
	private Date realDoneDate;		// 实际完成日期 -- '
	private String isDone;		// 是否完成 -- '
	private String delayDays;		// 延期天数 -- '
	private String delayType;		// 延期原因分类 -- '
	private String delayReason;		// 延期原因描述 -- '
	private Date planCheckTime;//计划考核日期

	public Date getPlanCheckTime() {
		return planCheckTime;
	}

	public void setPlanCheckTime(Date planCheckTime) {
		this.planCheckTime = planCheckTime;
	}

	private ProgressKanban progressKanban;
	
	private String realLessPlan;
	private String realLessExe;
	
	public BizNodePlan() {
	}

	public BizNodePlan(Integer id){
		super(id);
	}

	
	@Length(min=0, max=50, message="节点名称 -- '长度必须介于 0 和 50 之间")
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	@Length(min=0, max=11, message="节点序号 -- '长度必须介于 0 和 11 之间")
	public String getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(String nodeIndex) {
		this.nodeIndex = nodeIndex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanDoneDate() {
		return planDoneDate;
	}

	public void setPlanDoneDate(Date planDoneDate) {
		this.planDoneDate = planDoneDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExeDoneDate() {
		return exeDoneDate;
	}

	public void setExeDoneDate(Date exeDoneDate) {
		this.exeDoneDate = exeDoneDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealDoneDate() {
		return realDoneDate;
	}

	public String getRealLessPlan() {
		return realLessPlan;
	}

	public void setRealLessPlan(String realLessPlan) {
		this.realLessPlan = realLessPlan;
	}

	public String getRealLessExe() {
		return realLessExe;
	}

	public void setRealLessExe(String realLessExe) {
		this.realLessExe = realLessExe;
	}

	public void setRealDoneDate(Date realDoneDate) {
		this.realDoneDate = realDoneDate;
	}
	
	@Length(min=0, max=10, message="是否完成 -- '长度必须介于 0 和 10 之间")
	public String getIsDone() {
		return isDone;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}
	
	@Length(min=0, max=11, message="延期天数 -- '长度必须介于 0 和 11 之间")
	public String getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	
	@Length(min=0, max=10, message="延期原因分类 -- '长度必须介于 0 和 10 之间")
	public String getDelayType() {
		return delayType;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}
	
	@Length(min=0, max=255, message="延期原因描述 -- '长度必须介于 0 和 255 之间")
	public String getDelayReason() {
		return delayReason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}

	public ProgressKanban getProgressKanban() {
		return progressKanban;
	}

	public void setProgressKanban(ProgressKanban progressKanban) {
		this.progressKanban = progressKanban;
	}

	@Override
	public String toString() {
		return "BizNodePlanProject [id=" + id + ", nodeName=" + nodeName + ", nodeIndex=" + nodeIndex + ", planDoneDate="
				+ planDoneDate + ", exeDoneDate=" + exeDoneDate + ", realDoneDate=" + realDoneDate + ", delayDays="
				+ delayDays + "]";
	}

	
}