package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class Inspector extends DataEntity2<Inspector>{

	
	/**
	 * @author wang
	 * @version 2016-10-10
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;// 标识
	private String no; // 员工编号
	private String loginname; // 登录名
	private String realname; // 真实姓名
	private String phone; // 手机号
	private Integer empType; // 员工类型
	private String idcardno; // 员工身份证
	private String address; // 地址
	private String pointXy; //坐标
	private String headpic; // 头像
	private Integer star; // 星级
	private Integer sort; // 排名
	private String nps; // NPS值
	private String orderarea; // 订单区域
	private String orderstop; // 是否停单
	private Integer checkingCount; //在检施工数
	private Integer checkedCount;  //累计检查工地数
	private double distance; // 住址和工地距离
	private String storeid; // 门店Id
	private String projectMode;//工程模式 -- '1-产业模式；2-传统模式；3-全部
	private String orderPointx; //经度
	private String orderPointy; //纬度
	private Integer enginDepartId;//工程部id
	private Integer exchangeOrderTimes;//被换单次数
	
	
	public String getOrderPointx() {
		return orderPointx;
	}
	public void setOrderPointx(String orderPointx) {
		this.orderPointx = orderPointx;
	}
	public String getOrderPointy() {
		return orderPointy;
	}
	public void setOrderPointy(String orderPointy) {
		this.orderPointy = orderPointy;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
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
	public String getPointXy() {
		return pointXy;
	}
	public void setPointXy(String pointXy) {
		this.pointXy = pointXy;
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
	public String getOrderarea() {
		return orderarea;
	}
	public void setOrderarea(String orderarea) {
		this.orderarea = orderarea;
	}
	public String getOrderstop() {
		return orderstop;
	}
	public void setOrderstop(String orderstop) {
		this.orderstop = orderstop;
	}
	public Integer getCheckingCount() {
		return checkingCount;
	}
	public void setCheckingCount(Integer checkingCount) {
		this.checkingCount = checkingCount;
	}
	public Integer getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(Integer checkedCount) {
		this.checkedCount = checkedCount;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public Integer getExchangeOrderTimes() {
		return exchangeOrderTimes;
	}
	public void setExchangeOrderTimes(Integer exchangeOrderTimes) {
		this.exchangeOrderTimes = exchangeOrderTimes;
	}
	
}
