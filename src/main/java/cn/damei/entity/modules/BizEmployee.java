/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 员工信息Entity
 * 
 * @author qhy
 * @version 2016-08-24
 */
public class BizEmployee extends DataEntity<BizEmployee> {
	
	private static final long serialVersionUID = 1L;
	private String id;// 标识
	private String no; // 员工编号
	private String loginname; // 登录名
	private String realname; // 真实姓名
	private String phone; // 手机号
	private Integer empType; // 员工类型
	private String idcardno; // 员工身份证
	private Date entrytime; // 入职时间
	private Date birthday; // 生日
	private Integer sex; // 性别
	private String household; // 户口所在地
	private String roots; // 籍贯
	private String family; // 民族
	private Integer age; // 年龄
	private String education; // 学历
	private String postno; // 邮编
	private String address; // 地址
	private String headpic; // 头像
	private Integer star; // 星级
	private Integer sort; // 排名
	private String nps; // NPS值
	private Integer iswebchat; // 是否会微信
	private String orderarea; // 订单区域
	private Integer housetype; // 房屋类型
	private String orderstop; // 是否停单
	private Integer smartphone; // 是否智能手机
	private Integer worktype; // 工种
	private Integer electricancard; // 是否有电工证
	private Integer ncms; // 是否加入新农合
	private Double salarypoint; // 工资比例
	private Integer grouplead; // 是否组长
	private String storeid; // 门店Id
	private String sysuserid; // 系统用户Id
	private String managerid; // 推荐项目经理Id
	private String noValid;// 验证需要no
	private String loginNameValid;// 验证需要登录名
	private String phoneValid;// 验证需要手机号
	private String idcardnoValid;// 验证需要身份证
	private List<BizEmpArea> areas;// 区域列表
	private String projectMode;//工程模式 -- '1-产业模式；2-传统模式；3-全部
	private String value;//员工id用于下拉列表框
	
	private String label;// 员工姓名用于下拉列表框

	private String pointXy;//地图经纬坐标
	private String noInDepartment; //没有加入工程部的项目经理
	
	private String departmentName;
	private Integer workerIntroduceCount;
	private Integer noRelateCard;
	private Integer noRelateGroup;
	
	private Integer enginDepartId;
	
	private String employeeIdMappingOrderChangeSys;	//同步客服映射ID
	
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	//换单次数
	private Integer exchangeOrderTimes;
	private Date startExchanegeDate;  //按被换时间查询-开始
	private Date endExchanegeDate;    //按被换时间查询-结束
	
	
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

	private String noInGroup;//没有加入工人组的员工
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

//	public string getpoint_xy() {
//		return point_xy;
//	}

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

//	public void setPoint_xy(String point_xy) {
//		this.point_xy = point_xy;
//	}

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