/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 任务包模板管理Entity
 * @author wangchao
 * @version 2016-09-03
 */
public class BizTaskPackageTemplatRelProcedure extends DataEntity<BizTaskPackageTemplatRelProcedure> {
	
	private static final long serialVersionUID = 1L;
	private String taskPackageTemplatId;		// 任务包模板id 父类
	private String procedureNo;		// 工序编号
	private String procedureName;		// 工序名称
	
	public BizTaskPackageTemplatRelProcedure() {
		super();
	}

	public BizTaskPackageTemplatRelProcedure(String id){
		super(id);
	}

	public String getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(String taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	public String getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
}