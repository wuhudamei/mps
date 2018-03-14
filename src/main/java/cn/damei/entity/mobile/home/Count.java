package cn.damei.entity.mobile.home;

import java.io.Serializable;


/**
 * 首页未读信息Entity
 * @author wyb
 * @version 2016-11-16
 */
public class Count implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String customerName;	//客户姓名
	private String customerPhone; //客户电话
	
	private Integer reportCount;//质检报告未读
	private Integer progressCount;//施工进度未读
	private Integer projectChangeCount;//施工变更未读
	private Integer evalCount;//未评价
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