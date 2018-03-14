package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class Manager extends DataEntity2<Manager>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String no; // 员工编号
	private String loginname; // 登录名
	private String realname; // 真实姓名
	private String phone; // 手机号
	private String password;
	private Integer empType; // 员工类型
	private String idcardno; // 员工身份证
	//private Date entrytime; // 入职时间
	//private Date birthday; // 生日
	//private Integer sex; // 性别
	//private String household; // 户口所在地
	//private String roots; // 籍贯
	//private String family; // 民族
	//private Integer age; // 年龄
	//private String education; // 学历
	//private String postno; // 邮编
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
	//private Integer worktype; // 工种
	//private Integer electricancard; // 是否有电工证
	//private Integer ncms; // 是否加入新农合
	//private Double salarypoint; // 工资比例
	//private Integer grouplead; // 是否组长
	private String storeid; // 门店Id
	//private String sysuserid; // 系统用户Id
	//private String managerid; // 推荐项目经理Id
	//private String noValid;// 验证需要no
	//private String loginNameValid;// 验证需要登录名
	//private String phoneValid;// 验证需要手机号
	//private List<BizEmpArea> areas;// 区域列表
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
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getNps() {
		return nps;
	}
	public void setNps(String nps) {
		this.nps = nps;
	}
	public Integer getIswebchat() {
		return iswebchat;
	}
	public void setIswebchat(Integer iswebchat) {
		this.iswebchat = iswebchat;
	}
	public String getOrderarea() {
		return orderarea;
	}
	public void setOrderarea(String orderarea) {
		this.orderarea = orderarea;
	}
	public Integer getHousetype() {
		return housetype;
	}
	public void setHousetype(Integer housetype) {
		this.housetype = housetype;
	}
	public String getOrderstop() {
		return orderstop;
	}
	public void setOrderstop(String orderstop) {
		this.orderstop = orderstop;
	}
	public Integer getSmartphone() {
		return smartphone;
	}
	public void setSmartphone(Integer smartphone) {
		this.smartphone = smartphone;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	
	
}
