
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizNodePlanProject extends DataEntity2<BizNodePlanProject> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;
	private String nodeName;
	private String nodeIndex;
	private Date planDoneDate;
	private Date exeDoneDate;
	private Date realDoneDate;
	private String isDone;
	private String delayDays;
	private String delayType;
	private String delayReason;
	private Integer planDiffDay;
	
	private BizTraditionOrder bizTraditionOrder;
	
	
	
	public BizNodePlanProject() {
	}

	public BizNodePlanProject(Integer id){
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

	public Integer getPlanDiffDay() {
		return planDiffDay;
	}

	public void setPlanDiffDay(Integer planDiffDay) {
		this.planDiffDay = planDiffDay;
	}

	public BizTraditionOrder getBizTraditionOrder() {
		return bizTraditionOrder;
	}

	public void setBizTraditionOrder(BizTraditionOrder bizTraditionOrder) {
		this.bizTraditionOrder = bizTraditionOrder;
	}
	
	

	
}