
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizTaskPackageTemplatRelProcedure extends DataEntity<BizTaskPackageTemplatRelProcedure> {
	
	private static final long serialVersionUID = 1L;
	private String taskPackageTemplatId;
	private String procedureNo;
	private String procedureName;
	
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