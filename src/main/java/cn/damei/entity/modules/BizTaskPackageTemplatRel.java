/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 任务包模板管理Entity
 * @author wangchao
 * @version 2016-09-03
 */
public class BizTaskPackageTemplatRel extends DataEntity<BizTaskPackageTemplatRel> {
	
	private static final long serialVersionUID = 1L;
	private String taskPackageTemplatId;		// 任务包模板id 父类
	private String procedureNo;		// 工序编号
	
	public BizTaskPackageTemplatRel() {
		super();
	}

	public BizTaskPackageTemplatRel(String id){
		super(id);
	}

	public String getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(String taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	@Length(min=1, max=18, message="工序编号长度必须介于 1 和 11 之间")
	public String getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}
}