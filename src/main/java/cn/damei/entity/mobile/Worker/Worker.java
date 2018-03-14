package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

public class Worker extends DataEntity2<Worker>{
	

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String no;
	private String loginname;
	private String realname;
	private String phone;
	private Integer empType;
	private String idcardno;









	private String address;
	private String headpic;
	private Integer star;







	private Integer worktype;



	private Integer grouplead;
	private String storeid;






	private Integer emgrouprelationId;
	
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
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getEmpType() {
		return empType;
	}
	public void setEmpType(Integer empType) {
		this.empType = empType;
	}
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public Integer getGrouplead() {
		return grouplead;
	}
	public void setGrouplead(Integer grouplead) {
		this.grouplead = grouplead;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getWorktype() {
		return worktype;
	}
	public void setWorktype(Integer worktype) {
		this.worktype = worktype;
	}
	public Integer getEmgrouprelationId() {
		return emgrouprelationId;
	}
	public void setEmgrouprelationId(Integer emgrouprelationId) {
		this.emgrouprelationId = emgrouprelationId;
	}
	
}
