
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizEmployeeStarLog extends DataEntity<BizEmployeeStarLog> {
	

	private static final long serialVersionUID = 1L;
	private Integer empId;
	private String starChangeSource;
	private Integer relatedBusinessId;
	private String starScoreBefore;
	private String starScoreAfter;
	private Integer starBefore;
	private Integer starAfter;
	private String reasonType;
	private String changeDescribe;
	private Integer changeEmpId;
	private String starChangeDatetime;
	public BizEmployeeStarLog() {
		super();
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getStarChangeSource() {
		return starChangeSource;
	}
	public void setStarChangeSource(String starChangeSource) {
		this.starChangeSource = starChangeSource;
	}
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public String getStarScoreBefore() {
		return starScoreBefore;
	}
	public void setStarScoreBefore(String starScoreBefore) {
		this.starScoreBefore = starScoreBefore;
	}
	public String getStarScoreAfter() {
		return starScoreAfter;
	}
	public void setStarScoreAfter(String starScoreAfter) {
		this.starScoreAfter = starScoreAfter;
	}
	public Integer getStarBefore() {
		return starBefore;
	}
	public void setStarBefore(Integer starBefore) {
		this.starBefore = starBefore;
	}
	public Integer getStarAfter() {
		return starAfter;
	}
	public void setStarAfter(Integer starAfter) {
		this.starAfter = starAfter;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getChangeDescribe() {
		return changeDescribe;
	}
	public void setChangeDescribe(String changeDescribe) {
		this.changeDescribe = changeDescribe;
	}
	public Integer getChangeEmpId() {
		return changeEmpId;
	}
	public void setChangeEmpId(Integer changeEmpId) {
		this.changeEmpId = changeEmpId;
	}
	public String getStarChangeDatetime() {
		return starChangeDatetime;
	}
	public void setStarChangeDatetime(String starChangeDatetime) {
		this.starChangeDatetime = starChangeDatetime;
	}

}