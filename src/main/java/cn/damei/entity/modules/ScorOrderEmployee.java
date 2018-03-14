package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class ScorOrderEmployee extends DataEntity<ScorOrderEmployee>{
	private String no; //员工编号
	private String name;//门店名字
	private String storeId;//门店ID
	private String employeeName;//员工名字
	private Integer goodRate;//好评率
	private Integer goodNum;//好评数
	private Integer badNum;//差评数
	private double avgScore;//平均值
	private String employeePost; //岗位
	private Integer scoreBegin; //分值区间
	private Integer scoreEnd;
	private String query;//查询条件
	private String  employeePhone;//电话
	
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
