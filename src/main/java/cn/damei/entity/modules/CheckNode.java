
package cn.damei.entity.modules;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class CheckNode extends DataEntity2<CheckNode> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String qcCheckNodeName;
	private Integer qcCheckNodeIndex;
	private Integer daysToCheck;

	private Integer constructionScheduleId;
	private String constructionScheduleName;




	private String isUrgePay;
	private String status;
	private Date statusDatetime;
	
	
	private String projectMode;



	private Integer daysToApply;

	private String isForBasicWork;

	public String getIsForBasicWork() {
		return isForBasicWork;
	}

	public void setIsForBasicWork(String isForBasicWork) {
		this.isForBasicWork = isForBasicWork;
	}

	public Integer getDaysToApply() {
		return daysToApply;
	}

	public void setDaysToApply(Integer daysToApply) {
		this.daysToApply = daysToApply;
	}

	public CheckNode() {
		super();
	}

	public CheckNode(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
	
	public Integer getQcCheckNodeIndex() {
		return qcCheckNodeIndex;
	}

	public void setQcCheckNodeIndex(Integer qcCheckNodeIndex) {
		this.qcCheckNodeIndex = qcCheckNodeIndex;
	}
	
	public Integer getDaysToCheck() {
		return daysToCheck;
	}

	public void setDaysToCheck(Integer daysToCheck) {
		this.daysToCheck = daysToCheck;
	}
	
	public Integer getConstructionScheduleId() {
		return constructionScheduleId;
	}

	public void setConstructionScheduleId(Integer constructionScheduleId) {
		this.constructionScheduleId = constructionScheduleId;
	}
	
	public String getIsUrgePay() {
		return isUrgePay;
	}

	public void setIsUrgePay(String isUrgePay) {
		this.isUrgePay = isUrgePay;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	public String getConstructionScheduleName() {
		return constructionScheduleName;
	}

	public void setConstructionScheduleName(String constructionScheduleName) {
		this.constructionScheduleName = constructionScheduleName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}