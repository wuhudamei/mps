
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class BizOrderInstasllPlanAndProblem extends DataEntity2<BizOrderInstasllPlanAndProblem> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer orderInstallPlanId;
	private String installItemSequence;
	private Date applyIntoCreateDatetime;
	private Date applyIntoDate;
	private Date realIntoDate;
	private Date realCompleteDate;
	private Integer problemCount;
	private String problemDescribe;
	private Date problemDateTime;
	
	

	public BizOrderInstasllPlanAndProblem() {
		super();
	}

	public BizOrderInstasllPlanAndProblem(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderInstallPlanId() {
		return orderInstallPlanId;
	}

	public void setOrderInstallPlanId(Integer orderInstallPlanId) {
		this.orderInstallPlanId = orderInstallPlanId;
	}

	public String getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(String installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	public Date getApplyIntoCreateDatetime() {
		return applyIntoCreateDatetime;
	}

	public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
		this.applyIntoCreateDatetime = applyIntoCreateDatetime;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getRealIntoDate() {
		return realIntoDate;
	}

	public void setRealIntoDate(Date realIntoDate) {
		this.realIntoDate = realIntoDate;
	}

	public Date getRealCompleteDate() {
		return realCompleteDate;
	}

	public void setRealCompleteDate(Date realCompleteDate) {
		this.realCompleteDate = realCompleteDate;
	}

	public Integer getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(Integer problemCount) {
		this.problemCount = problemCount;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	public Date getProblemDateTime() {
		return problemDateTime;
	}

	public void setProblemDateTime(Date problemDateTime) {
		this.problemDateTime = problemDateTime;
	}
	
}