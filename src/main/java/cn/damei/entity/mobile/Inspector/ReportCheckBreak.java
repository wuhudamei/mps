package cn.damei.entity.mobile.Inspector;


import cn.damei.common.persistence.DataEntity2;


public class ReportCheckBreak extends DataEntity2<ReportCheckBreak>{

	private static final long serialVersionUID = 1L;
	private Integer qcBillCheckItemId;
	private Integer qcCheckItemBreakId;
	private String breakDescribe;
	private String isRequiredRemarks;
	private String breakRemarks;
	
	private ReportCheckDetails reportCheck;
	
	
	public ReportCheckDetails getReportCheck() {
		return reportCheck;
	}
	public void setReportCheck(ReportCheckDetails reportCheck) {
		this.reportCheck = reportCheck;
	}
	public Integer getQcBillCheckItemId() {
		return qcBillCheckItemId;
	}
	public void setQcBillCheckItemId(Integer qcBillCheckItemId) {
		this.qcBillCheckItemId = qcBillCheckItemId;
	}
	public Integer getQcCheckItemBreakId() {
		return qcCheckItemBreakId;
	}
	public void setQcCheckItemBreakId(Integer qcCheckItemBreakId) {
		this.qcCheckItemBreakId = qcCheckItemBreakId;
	}
	public String getBreakDescribe() {
		return breakDescribe;
	}
	public void setBreakDescribe(String breakDescribe) {
		this.breakDescribe = breakDescribe;
	}
	public String getIsRequiredRemarks() {
		return isRequiredRemarks;
	}
	public void setIsRequiredRemarks(String isRequiredRemarks) {
		this.isRequiredRemarks = isRequiredRemarks;
	}
	public String getBreakRemarks() {
		return breakRemarks;
	}
	public void setBreakRemarks(String breakRemarks) {
		this.breakRemarks = breakRemarks;
	}
	 
	
	
}
