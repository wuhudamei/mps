
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class BizEmployee extends DataEntity<BizEmployee> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String no;
	private String loginname;
	private String realname;
	private String phone;
	private Integer empType;
	private String idcardno;
	private Date entrytime;
	private Date birthday;
	private Integer sex;
	private String household;
	private String roots;
	private String family;
	private Integer age;
	private String education;
	private String postno;
	private String address;
	private String headpic;
	private Integer star;
	private Integer sort;
	private String nps;
	private Integer iswebchat;
	private String orderarea;
	private Integer housetype;
	private String orderstop;
	private Integer smartphone;
	private Integer worktype;
	private Integer electricancard;
	private Integer ncms;
	private Double salarypoint;
	private Integer grouplead;
	private String storeid;
	private String sysuserid;
	private String managerid;
	private String noValid;
	private String loginNameValid;
	private String phoneValid;
	private String idcardnoValid;
	private List<BizEmpArea> areas;
	private String projectMode;
	private String value;
	
	private String label;

	private String pointXy;
	private String noInDepartment;
	
	private String departmentName;
	private Integer workerIntroduceCount;
	private Integer noRelateCard;
	private Integer noRelateGroup;
	
	private Integer enginDepartId;
	
	private String employeeIdMappingOrderChangeSys;
	
	private List<Integer> enginDepartIds = new ArrayList<Integer>();

	private Integer exchangeOrderTimes;
	private Date startExchanegeDate;
	private Date endExchanegeDate;
	
	
	public Date getStartExchanegeDate() {
		return startExchanegeDate;
	}

	public void setStartExchanegeDate(Date startExchanegeDate) {
		this.startExchanegeDate = startExchanegeDate;
	}

	public Date getEndExchanegeDate() {
		return endExchanegeDate;
	}

	public void setEndExchanegeDate(Date endExchanegeDate) {
		this.endExchanegeDate = endExchanegeDate;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getPointXy() {
		return pointXy;
	}
	
	public void setPointXy(String pointXy) {
		this.pointXy = pointXy;
	}

	private String noInGroup;
	public String getNoInGroup() {
		return noInGroup;
	}

	public void setNoInGroup(String noInGroup) {
		this.noInGroup = noInGroup;
	}

	public BizEmployee() {
		super();
	}

	public BizEmployee(String id) {
		super(id);
	}
	@Length(min = 0, max = 255, message = "地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public Integer getAge() {
		return age;
	}

	public List<BizEmpArea> getAreas() {
		return areas;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	@Length(min = 0, max = 50, message = "学历长度必须介于 0 和 50 之间")
	public String getEducation() {
		return education;
	}

	public Integer getElectricancard() {
		return electricancard;
	}
	
	public Integer getEmpType() {
		return empType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntrytime() {
		return entrytime;
	}

	@Length(min = 0, max = 20, message = "民族长度必须介于 0 和 20 之间")
	public String getFamily() {
		return family;
	}

	public Integer getGrouplead() {
		return grouplead;
	}

	@Length(min = 0, max = 255, message = "头像长度必须介于 0 和 255 之间")
	public String getHeadpic() {
		return headpic;
	}

	@Length(min = 0, max = 255, message = "户口所在地长度必须介于 0 和 255 之间")
	public String getHousehold() {
		return household;
	}

	public Integer getHousetype() {
		return housetype;
	}

	public String getId() {
		return id;
	}

	@Length(min = 0, max = 18, message = "员工身份证长度必须介于 0 和 18 之间")
	public String getIdcardno() {
		return idcardno;
	}

	public Integer getIswebchat() {
		return iswebchat;
	}

	public String getLabel() {
		return label;
	}

	@Length(min = 0, max = 255, message = "登录名长度必须介于 0 和 255 之间")
	public String getLoginname() {
		return loginname;
	}

	public String getLoginNameValid() {
		return loginNameValid;
	}

	public String getManagerid() {
		return managerid;
	}

	public Integer getNcms() {
		return ncms;
	}

	@Length(min = 0, max = 18, message = "员工编号长度必须介于 0 和 18 之间")
	public String getNo() {
		return no;
	}

	public String getNoValid() {
		return noValid;
	}

	@Length(min = 0, max = 50, message = "NPS值长度必须介于 0 和 50 之间")
	public String getNps() {
		return nps;
	}

	@Length(min = 0, max = 255, message = "订单区域长度必须介于 0 和 255 之间")
	public String getOrderarea() {
		return orderarea;
	}

	@Length(min = 0, max = 2, message = "是否停单长度必须介于 0 和 2 之间")
	public String getOrderstop() {
		return orderstop;
	}

	@Length(min = 0, max = 11, message = "手机号长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public String getPhoneValid() {
		return phoneValid;
	}





	@Length(min = 0, max = 50, message = "邮编长度必须介于 0 和 50 之间")
	public String getPostno() {
		return postno;
	}

	@Length(min = 0, max = 255, message = "真实姓名长度必须介于 0 和 255 之间")
	public String getRealname() {
		return realname;
	}

	@Length(min = 0, max = 255, message = "籍贯长度必须介于 0 和 255 之间")
	public String getRoots() {
		return roots;
	}

	public Double getSalarypoint() {
		return salarypoint;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getSmartphone() {
		return smartphone;
	}

	public Integer getSort() {
		return sort;
	}

	public Integer getStar() {
		return star;
	}

	@Length(min = 0, max = 64, message = "门店Id长度必须介于 0 和 64 之间")
	public String getStoreid() {
		return storeid;
	}

	@Length(min = 0, max = 64, message = "系统用户Id长度必须介于 0 和 64 之间")
	public String getSysuserid() {
		return sysuserid;
	}

	public String getValue() {
		return value;
	}

	public Integer getWorktype() {
		return worktype;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setAreas(List<BizEmpArea> areas) {
		this.areas = areas;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setElectricancard(Integer electricancard) {
		this.electricancard = electricancard;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
	}

	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setGrouplead(Integer grouplead) {
		this.grouplead = grouplead;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public void setHousetype(Integer housetype) {
		this.housetype = housetype;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public void setIswebchat(Integer iswebchat) {
		this.iswebchat = iswebchat;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setLoginNameValid(String loginNameValid) {
		this.loginNameValid = loginNameValid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public void setNcms(Integer ncms) {
		this.ncms = ncms;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setNoValid(String noValid) {
		this.noValid = noValid;
	}

	public void setNps(String nps) {
		this.nps = nps;
	}

	public void setOrderarea(String orderarea) {
		this.orderarea = orderarea;
	}

	public void setOrderstop(String orderstop) {
		this.orderstop = orderstop;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhoneValid(String phoneValid) {
		this.phoneValid = phoneValid;
	}





	public void setPostno(String postno) {
		this.postno = postno;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setRoots(String roots) {
		this.roots = roots;
	}

	public void setSalarypoint(Double salarypoint) {
		this.salarypoint = salarypoint;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setSmartphone(Integer smartphone) {
		this.smartphone = smartphone;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public void setSysuserid(String sysuserid) {
		this.sysuserid = sysuserid;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setWorktype(Integer worktype) {
		this.worktype = worktype;
	}

	public String getIdcardnoValid() {
		return idcardnoValid;
	}

	public void setIdcardnoValid(String idcardnoValid) {
		this.idcardnoValid = idcardnoValid;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getNoInDepartment() {
		return noInDepartment;
	}

	public void setNoInDepartment(String noInDepartment) {
		this.noInDepartment = noInDepartment;
	}

	public Integer getWorkerIntroduceCount() {
		return workerIntroduceCount;
	}

	public void setWorkerIntroduceCount(Integer workerIntroduceCount) {
		this.workerIntroduceCount = workerIntroduceCount;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public Integer getNoRelateCard() {
		return noRelateCard;
	}

	public void setNoRelateCard(Integer noRelateCard) {
		this.noRelateCard = noRelateCard;
	}

	public Integer getNoRelateGroup() {
		return noRelateGroup;
	}

	public void setNoRelateGroup(Integer noRelateGroup) {
		this.noRelateGroup = noRelateGroup;
	}

	public String getEmployeeIdMappingOrderChangeSys() {
		return employeeIdMappingOrderChangeSys;
	}

	public void setEmployeeIdMappingOrderChangeSys(String employeeIdMappingOrderChangeSys) {
		this.employeeIdMappingOrderChangeSys = employeeIdMappingOrderChangeSys;
	}

	public Integer getExchangeOrderTimes() {
		return exchangeOrderTimes;
	}

	public void setExchangeOrderTimes(Integer exchangeOrderTimes) {
		this.exchangeOrderTimes = exchangeOrderTimes;
	}
	
	
}