/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价指标设置Entity
 * @author wyb
 * @version 2017-02-24
 */
public class BizEvalIndex extends DataEntity2<BizEvalIndex> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private String projectMode;		// 工程模式
	private String indexName;		// 评价指标
	private String isEnabled; 		//是否启用
	public BizEvalIndex() {
		super();
	}

	public BizEvalIndex(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="评价指标长度必须介于 0 和 100 之间")
	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}