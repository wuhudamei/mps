package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class Inspector  extends DataEntity2<Inspector>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id; 
	private String no;
	private String loginName;
	private String realName;
	private String phone;
	private String password;
	private Integer empType;
	private Integer storeId;
	private Integer totalOrderCount;
	private Integer  currentOrderCount;
	private Integer inspectReport;
	private Integer evalCount;
	
	private Date nowDate;
	private Integer isLeader;
	
	private Integer star;
	private String selectCheckText;
	
	
	public String getSelectCheckText() {
		return selectCheckText;
	}
	public void setSelectCheckText(String selectCheckText) {
		this.selectCheckText = selectCheckText;
	}
	public Integer getInspectReport() {
		return inspectReport;
	}
	public void setInspectReport(Integer inspectReport) {
		this.inspectReport = inspectReport;
	}
	public Integer getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEmpType() {
		return empType;
	}
	public void setEmpType(Integer empType) {
		this.empType = empType;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getTotalOrderCount() {
		return totalOrderCount;
	}
	public void setTotalOrderCount(Integer totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	public Integer getCurrentOrderCount() {
		return currentOrderCount;
	}
	public void setCurrentOrderCount(Integer currentOrderCount) {
		this.currentOrderCount = currentOrderCount;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getEvalCount() {
		return evalCount;
	}
	public void setEvalCount(Integer evalCount) {
		this.evalCount = evalCount;
	}
	public Date getNowDate() {
		return nowDate;
	}
	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
	
	
}
