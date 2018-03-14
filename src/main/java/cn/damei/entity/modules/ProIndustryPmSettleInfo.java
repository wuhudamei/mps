package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 准产业订单项目经理结算信息
 * @author hyh
 *
 */
public class ProIndustryPmSettleInfo extends DataEntity2<ProIndustryPmSettleInfo>{
	private static final long serialVersionUID = 1L;
	private Integer orderId;  //订单Id
	private Integer storeId;  //门店
	private Integer projectMode; //工程模式
	private String orderNum; //订单编号
	private Integer enginDepartId; //区域
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	
	private String customerName;	// 客户姓名
	private String customerPhone;	// 客户手机号
	private String itemCustomer;    // 项目经理
	private String itemPhone;       //项目经理手机号
	private String inspector;       //质检员
	private Date acceptCheckDatetime; //约检节点质检员确认通过时间
	private Date completeAuditPassTime;//竣工申请通过时间
	private Double secondPayment; //二期款
	private Date secondPaymentDate; //二期款时间
	private Double finalPayment;  //尾款
	private Date finalPaymentDate;  //尾款时间
	
	public Date getCompleteAuditPassTime() {
		return completeAuditPassTime;
	}
	public void setCompleteAuditPassTime(Date completeAuditPassTime) {
		this.completeAuditPassTime = completeAuditPassTime;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}
	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	public String getItemPhone() {
		return itemPhone;
	}
	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}
	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}
	public Double getSecondPayment() {
		return secondPayment;
	}
	public void setSecondPayment(Double secondPayment) {
		this.secondPayment = secondPayment;
	}
	public Date getSecondPaymentDate() {
		return secondPaymentDate;
	}
	public void setSecondPaymentDate(Date secondPaymentDate) {
		this.secondPaymentDate = secondPaymentDate;
	}
	public Double getFinalPayment() {
		return finalPayment;
	}
	public void setFinalPayment(Double finalPayment) {
		this.finalPayment = finalPayment;
	}
	public Date getFinalPaymentDate() {
		return finalPaymentDate;
	}
	public void setFinalPaymentDate(Date finalPaymentDate) {
		this.finalPaymentDate = finalPaymentDate;
	}
	
	
}
