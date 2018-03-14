package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class ScorOrderEmployee extends DataEntity<ScorOrderEmployee>{
	private String no;
	private String name;
	private String storeId;
	private String employeeName;
	private Integer goodRate;
	private Integer goodNum;
	private Integer badNum;
	private double avgScore;
	private String employeePost;
	private Integer scoreBegin;
	private Integer scoreEnd;
	private String query;
	private String  employeePhone;
	
	public String getEmployeePost() {
		return employeePost;
	}
	public void setEmployeePost(String employeePost) {
		this.employeePost = employeePost;
	}
	public String getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getGoodRate() {
		return goodRate;
	}
	public void setGoodRate(Integer goodRate) {
		this.goodRate = goodRate;
	}
	public Integer getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}
	public Integer getBadNum() {
		return badNum;
	}
	public void setBadNum(Integer badNum) {
		this.badNum = badNum;
	}
	@Override
	public String toString() {
		return "ScorOrderEmployee [no=" + no + ", name=" + name + ", storeId=" + storeId + ", employeeName="
				+ employeeName + ", goodRate=" + goodRate + ", goodNum=" + goodNum + ", badNum=" + badNum
				+ ", avgScore=" + avgScore + ", employeePost=" + employeePost + ", scoreBegin=" + scoreBegin
				+ ", scoreEnd=" + scoreEnd + ", query=" + query + ", employeePhone=" + employeePhone + "]";
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public Integer getScoreBegin() {
		return scoreBegin;
	}
	public void setScoreBegin(Integer scoreBegin) {
		this.scoreBegin = scoreBegin;
	}

	public Integer getScoreEnd() {
		return scoreEnd;
	}
	public void setScoreEnd(Integer scoreEnd) {
		this.scoreEnd = scoreEnd;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
	

}
