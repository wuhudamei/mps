
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;




public class BizOrderInstallItemProblemLog extends DataEntity2<BizOrderInstallItemProblemLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessProblemId;
	private String status;
	private String problemSolveRole;
	private Integer problemSolveEmployeeId;
	private String problemSolveEmployeeName;
	private String problemSolveEmployeePhone;
	private String problemSolveNotes;
	private String statusName;
	private BizOrderInstallItemProblem bizOrderInstallItemProblem;
	
	private String realName;
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	

	public BizOrderInstallItemProblem getBizOrderInstallItemProblem() {
		return bizOrderInstallItemProblem;
	}

	public void setBizOrderInstallItemProblem(BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		this.bizOrderInstallItemProblem = bizOrderInstallItemProblem;
	}

	public BizOrderInstallItemProblemLog() {
		super();
	}

	public BizOrderInstallItemProblemLog(Integer id){
		super(id);
	}

	
	public Integer getBusinessProblemId() {
		return businessProblemId;
	}

	public void setBusinessProblemId(Integer businessProblemId) {
		this.businessProblemId = businessProblemId;
	}

	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=10, message="问题处理角色长度必须介于 0 和 10 之间")
	public String getProblemSolveRole() {
		return problemSolveRole;
	}

	public void setProblemSolveRole(String problemSolveRole) {
		this.problemSolveRole = problemSolveRole;
	}
	
	public Integer getProblemSolveEmployeeId() {
		return problemSolveEmployeeId;
	}

	public void setProblemSolveEmployeeId(Integer problemSolveEmployeeId) {
		this.problemSolveEmployeeId = problemSolveEmployeeId;
	}
	
	@Length(min=0, max=500, message="问题处理说明长度必须介于 0 和 500 之间")
	public String getProblemSolveNotes() {
		return problemSolveNotes;
	}

	public void setProblemSolveNotes(String problemSolveNotes) {
		this.problemSolveNotes = problemSolveNotes;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProblemSolveEmployeeName() {
		return problemSolveEmployeeName;
	}

	public void setProblemSolveEmployeeName(String problemSolveEmployeeName) {
		this.problemSolveEmployeeName = problemSolveEmployeeName;
	}

	public String getProblemSolveEmployeePhone() {
		return problemSolveEmployeePhone;
	}

	public void setProblemSolveEmployeePhone(String problemSolveEmployeePhone) {
		this.problemSolveEmployeePhone = problemSolveEmployeePhone;
	}
	
	
}