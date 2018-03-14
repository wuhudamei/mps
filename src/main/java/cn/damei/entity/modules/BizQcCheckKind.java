/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 检查分类Entity
 * @author wyb
 * @version 2016-10-26
 */
public class BizQcCheckKind extends DataEntity2<BizQcCheckKind> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店id -- '
	private String qcCheckKindName;		// 检查分类名称 -- '
	private String taskPackageTemplatId;		// 任务包模板ID '
	private String templatName;		// 任务包模板名称 '
	private String status;		// 状态 -- '0：停用；1：启用；默认1
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	public BizQcCheckKind() {
		super();
	}

	public BizQcCheckKind(Integer id){
		super(id);
	}

	@Length(min=0, max=64, message="门店id -- '长度必须介于 0 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=100, message="检查分类名称 -- '长度必须介于 0 和 100 之间")
	public String getQcCheckKindName() {
		return qcCheckKindName;
	}

	public void setQcCheckKindName(String qcCheckKindName) {
		this.qcCheckKindName = qcCheckKindName;
	}
	
	@Length(min=0, max=1, message="状态 -- '0：停用；1：启用；默认1长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}


	public String getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(String taskPackageTemplatId) {

			this.taskPackageTemplatId = taskPackageTemplatId;

	}

	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}
}