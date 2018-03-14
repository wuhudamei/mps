
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizStarSetting extends DataEntity2<BizStarSetting> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Double startScore;
	private Double endScore;
	private Integer star;
	private String isEnabled;
	private String projectMode;
	public BizStarSetting() {
		super();
	}

	public BizStarSetting(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	

	public Double getStartScore() {
		return startScore;
	}

	public void setStartScore(Double startScore) {
		this.startScore = startScore;
	}

	public Double getEndScore() {
		return endScore;
	}

	public void setEndScore(Double endScore) {
		this.endScore = endScore;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	
	
}