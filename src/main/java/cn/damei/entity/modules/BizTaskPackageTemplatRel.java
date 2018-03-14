
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizTaskPackageTemplatRel extends DataEntity<BizTaskPackageTemplatRel> {
	
	private static final long serialVersionUID = 1L;
	private String taskPackageTemplatId;
	private String procedureNo;
	
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