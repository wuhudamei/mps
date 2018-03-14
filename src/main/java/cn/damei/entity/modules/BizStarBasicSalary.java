package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BizStarBasicSalary extends DataEntity2<BizStarBasicSalary>{

	private static final long serialVersionUID = 1L;

	private Double basicSalary;

	private Date effectDate;

	private String version;

	private String status;

	private BizManagerStar bizManagerStar;

	private Integer starLevel;

	private Integer storeId;

	private String projectMode;
	
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BizManagerStar getBizManagerStar() {
		return bizManagerStar;
	}
	public void setBizManagerStar(BizManagerStar bizManagerStar) {
		this.bizManagerStar = bizManagerStar;
	}
	public Integer getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}
