
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderInstallItemProblem extends DataEntity2<BizOrderInstallItemProblem> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessOnlyMarkId;
	private Integer problemTypeId;
	private String isDelay;
	private Double delayDays;
	private String problemDescribe;
	private String status;
	private String typeName;
	private String businessType;
	private String problemSolveRole;
	private String problemSolveNotes;
	private Date logDate;
	
	
	private Date expectSolveDatetime;
	private String inchargeName;
	private Double punishScore;
	private Double punishMoney;
	private String punishRemarks;
	
	private String createDateString;
	
	private List<BizOrderInstallItemProblemLog> logList;
	
	
	
	public String getProblemSolveNotes() {
		return problemSolveNotes;
	}

	public void setProblemSolveNotes(String problemSolveNotes) {
		this.problemSolveNotes = problemSolveNotes;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getProblemSolveRole() {
		return problemSolveRole;
	}

	public void setProblemSolveRole(String problemSolveRole) {
		this.problemSolveRole = problemSolveRole;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<BizOrderInstallItemProblemLog> getLogList() {
		return logList;
	}

	public void setLogList(List<BizOrderInstallItemProblemLog> logList) {
		this.logList = logList;
	}

	public BizOrderInstallItemProblem() {
		super();
	}

	public BizOrderInstallItemProblem(Integer id){
		super(id);
	}
	
	public Integer getBusinessOnlyMarkId() {
		return businessOnlyMarkId;
	}

	public void setBusinessOnlyMarkId(Integer businessOnlyMarkId) {
		this.businessOnlyMarkId = businessOnlyMarkId;
	}

	public Integer getProblemTypeId() {
		return problemTypeId;
	}

	public void setProblemTypeId(Integer problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	
	@Length(min=0, max=1, message="是否延期长度必须介于 0 和 1 之间")
	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	
	public Double getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Double delayDays) {
		this.delayDays = delayDays;
	}
	
	@Length(min=0, max=500, message="问题描述长度必须介于 0 和 500 之间")
	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}
	
	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpectSolveDatetime() {
		return expectSolveDatetime;
	}

	public void setExpectSolveDatetime(Date expectSolveDatetime) {
		this.expectSolveDatetime = expectSolveDatetime;
	}

	public String getInchargeName() {
		return inchargeName;
	}

	public void setInchargeName(String inchargeName) {
		this.inchargeName = inchargeName;
	}

	public Double getPunishScore() {
		return punishScore;
	}

	public void setPunishScore(Double punishScore) {
		this.punishScore = punishScore;
	}

	public Double getPunishMoney() {
		return punishMoney;
	}

	public void setPunishMoney(Double punishMoney) {
		this.punishMoney = punishMoney;
	}

	public String getPunishRemarks() {
		return punishRemarks;
	}

	public void setPunishRemarks(String punishRemarks) {
		this.punishRemarks = punishRemarks;
	}

	public String getCreateDateString() {
		return createDateString;
	}

	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	
}