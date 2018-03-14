package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;



public class BizEvalWorkGrade extends DataEntity2<BizEvalWorkGrade>{

	private static final long serialVersionUID = 1L;
	
	private String relatedBusinessId;
	
	private String storeId;
	
	private String projectMode;
	
	private Integer enginDepartId;
	
	private String enginDepartName;
	
	private Date gradeDate;
	
	private String groupRealName;
	
	private String customerMessage;
	
	private String customerName;
	
	private Integer packageId;
	
	private String packageName;
	
	private Integer evalRoleType;
	
	private Double evaltotalScore;
	
	private Double gradtotalScore;
	
	private List<BizEvalActivityIndex> bizEvalActivityIndexList;
	
	private Date startDate;
	
	private Date endDate;
	
	private String manager;
	
	private String communityName;
	
	private String buildNumber;
	
	private String buildUnit;
	
	private String buildRoom;
	

	
	public String getRelatedBusinessId() {
		return relatedBusinessId;
	}

	public void setRelatedBusinessId(String relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getGradtotalScore() {
		return gradtotalScore;
	}

	public void setGradtotalScore(Double gradtotalScore) {
		this.gradtotalScore = gradtotalScore;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

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

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public Date getGradeDate() {
		return gradeDate;
	}

	public void setGradeDate(Date gradeDate) {
		this.gradeDate = gradeDate;
	}

	public String getGroupRealName() {
		return groupRealName;
	}

	public void setGroupRealName(String groupRealName) {
		this.groupRealName = groupRealName;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getEvalRoleType() {
		return evalRoleType;
	}

	public void setEvalRoleType(Integer evalRoleType) {
		this.evalRoleType = evalRoleType;
	}

	public Double getEvaltotalScore() {
		return evaltotalScore;
	}

	public void setEvaltotalScore(Double evaltotalScore) {
		this.evaltotalScore = evaltotalScore;
	}

	public List<BizEvalActivityIndex> getBizEvalActivityIndexList() {
		return bizEvalActivityIndexList;
	}

	public void setBizEvalActivityIndexList(List<BizEvalActivityIndex> bizEvalActivityIndexList) {
		this.bizEvalActivityIndexList = bizEvalActivityIndexList;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}
	
	
}
