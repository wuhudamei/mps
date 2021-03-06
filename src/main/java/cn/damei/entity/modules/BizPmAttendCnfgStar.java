
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizPmAttendCnfgStar extends DataEntity<BizPmAttendCnfgStar> {
	
	private static final long serialVersionUID = 1L;
	private String pmAttendCnfgId;		
	private String star;		
	private double starSalaryAllAttend;		
	private double starSalaryMin;		
	private String isEnabled;
	public String getPmAttendCnfgId() {
		return pmAttendCnfgId;
	}
	public void setPmAttendCnfgId(String pmAttendCnfgId) {
		this.pmAttendCnfgId = pmAttendCnfgId;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public double getStarSalaryAllAttend() {
		return starSalaryAllAttend;
	}
	public void setStarSalaryAllAttend(double starSalaryAllAttend) {
		this.starSalaryAllAttend = starSalaryAllAttend;
	}
	public double getStarSalaryMin() {
		return starSalaryMin;
	}
	public void setStarSalaryMin(double starSalaryMin) {
		this.starSalaryMin = starSalaryMin;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}	
	
	
}