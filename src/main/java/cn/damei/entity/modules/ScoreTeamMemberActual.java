
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class ScoreTeamMemberActual extends DataEntity<ScoreTeamMemberActual> {
	
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private Integer employeeId;
	private String employeeName;
	private String employeePhone;
	private Integer belongOffice;
	private String employeePost;
	private Integer currentScore;
	private Integer currentGoodNum;
	private Integer currentBadNum;
	private String initFlag;

	public ScoreTeamMemberActual() {
		super();
	}

	public ScoreTeamMemberActual(String id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public Integer getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(Integer currentScore) {
		this.currentScore = currentScore;
	}

	public Integer getCurrentGoodNum() {
		return currentGoodNum;
	}

	public void setCurrentGoodNum(Integer currentGoodNum) {
		this.currentGoodNum = currentGoodNum;
	}

	public Integer getCurrentBadNum() {
		return currentBadNum;
	}

	public void setCurrentBadNum(Integer currentBadNum) {
		this.currentBadNum = currentBadNum;
	}

	public String getInitFlag() {
		return initFlag;
	}

	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}

	public Integer getBelongOffice() {
		return belongOffice;
	}

	public void setBelongOffice(Integer belongOffice) {
		this.belongOffice = belongOffice;
	}

	public String getEmployeePost() {
		return employeePost;
	}

	public void setEmployeePost(String employeePost) {
		this.employeePost = employeePost;
	}
	
	
}