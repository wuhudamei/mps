/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价指标设置Entity
 * @author ws
 * @version 2017-09-05
 */
public class BizStarSetting extends DataEntity2<BizStarSetting> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private Double startScore;		// 起始分数
	private Double endScore;		// 结束分数
	private Integer star;			//星级
	private String isEnabled; 		//是否启用
	private String projectMode;		//工程模式
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