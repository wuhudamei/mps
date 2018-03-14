package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月24日 下午3:44:28 
* 质检员 
*/

public class Inspector  extends DataEntity2<Inspector>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id; 
	private String no; // 员工编号
	private String loginName; // 登录名
	private String realName; // 真实姓名
	private String phone; // 手机号
	private String password;
	private Integer empType; // 员工类型
	private Integer storeId; // 门店Id
	private Integer totalOrderCount;//总订单数
	private Integer  currentOrderCount;//正在施工数
	private Integer inspectReport;//质检报告
	private Integer evalCount;//未评价数
	
	private Date nowDate;	//当前时间
	private Integer isLeader;//是否领导
	
	private Integer star;//星级
	private String selectCheckText;	//抽检搜索条件
	
	
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
