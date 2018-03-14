/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 约检数量配置Entity
 * @author 梅浩
 * @version 2017-04-20
 */
public class BizQcMaxCount extends DataEntity<BizQcMaxCount> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店
	private String qcMaxCount;		// 最大约检数量
	
	public BizQcMaxCount() {
		super();
	}

	public BizQcMaxCount(String id){
		super(id);
	}

	@Length(min=0, max=11, message="门店长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=11, message="最大约检数量长度必须介于 0 和 11 之间")
	public String getQcMaxCount() {
		return qcMaxCount;
	}

	public void setQcMaxCount(String qcMaxCount) {
		this.qcMaxCount = qcMaxCount;
	}
	
}