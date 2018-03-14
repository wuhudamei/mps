/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 区域管理Entity
 * @author qhy
 * @version 2016-09-08
 */
public class BizArea extends DataEntity<BizArea> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 描述
	private String name;		// 区域名称
	private String storeId;		// 门店
	private String projectMode; // 工程模式 -- '1-产业模式；2-传统模式；3-全部
	
	public BizArea() {
		super();
	}

	public BizArea(String id){
		super(id);
	}

	@Length(min=1, max=100, message="描述长度必须介于 1 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=64, message="区域名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="门店长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
}