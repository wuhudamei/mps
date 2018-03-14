/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;



import cn.damei.common.persistence.DataEntity;

import java.util.Date;

/**
 * 约检问题上报查询Entity
 * @author mh
 * @version 2017-05-27
 */
public class BizBusinessProblemQuery extends DataEntity<BizBusinessProblemQuery> {
	
	private static final long serialVersionUID = 1L;
	private String businessOnlyMarkInt;
	private String problemTypeId;
	private String isDelay;
	private String delayDays;
	private String problemDescribe;
	private String status;
	private String businessType;


	private  Integer storeId;
	private Integer projectMode;
	private Date start;
	private Date end;
	private String pqcName;
	private String orderNumber;
	private String customerName;
	private String managerName;
	private Date issueReportStart;
	private Date issueReportEnd;
    private Integer checkNodeId;
	private String customerInfo;
	private String qcCheckNodeName;
	private String qcExpectCheckDate;
	private Date problemCreateDate;
	private String typeName;
	private Integer  issueTypeId;

	public Integer getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(Integer issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public String getQcExpectCheckDate() {
		return qcExpectCheckDate;
	}

	public void setQcExpectCheckDate(String qcExpectCheckDate) {
		this.qcExpectCheckDate = qcExpectCheckDate;
	}

	public Date getProblemCreateDate() {
		return problemCreateDate;
	}

	public void setProblemCreateDate(Date problemCreateDate) {
		this.problemCreateDate = problemCreateDate;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getPqcName() {
		return pqcName;
	}

	public void setPqcName(String pqcName) {
		this.pqcName = pqcName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Date getIssueReportStart() {
		return issueReportStart;
	}

	public void setIssueReportStart(Date issueReportStart) {
		this.issueReportStart = issueReportStart;
	}

	public Date getIssueReportEnd() {
		return issueReportEnd;
	}

	public void setIssueReportEnd(Date issueReportEnd) {
		this.issueReportEnd = issueReportEnd;
	}

	public Integer getCheckNodeId() {
		return checkNodeId;
	}

	public void setCheckNodeId(Integer checkNodeId) {
		this.checkNodeId = checkNodeId;
	}

	public String getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public void setBusinessOnlyMarkInt(String businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}

	public String getProblemTypeId() {
		return problemTypeId;
	}

	public void setProblemTypeId(String problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}

	public String getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}