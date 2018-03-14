package cn.damei.entity.mobile.home;

import java.io.Serializable;



public class Count implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String customerName;
	private String customerPhone;
	
	private Integer reportCount;
	private Integer progressCount;
	private Integer projectChangeCount;
	private Integer evalCount;
	private Integer projectProgressCount;
	
	public Integer getProjectProgressCount() {
		return projectProgressCount;
	}
	public void setProjectProgressCount(Integer projectProgressCount) {
		this.projectProgressCount = projectProgressCount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	public Integer getProgressCount() {
		return progressCount;
	}
	public void setProgressCount(Integer progressCount) {
		this.progressCount = progressCount;
	}
	public Integer getProjectChangeCount() {
		return projectChangeCount;
	}
	public void setProjectChangeCount(Integer projectChangeCount) {
		this.projectChangeCount = projectChangeCount;
	}
	public Integer getEvalCount() {
		return evalCount;
	}
	public void setEvalCount(Integer evalCount) {
		this.evalCount = evalCount;
	}
	
	
		
}