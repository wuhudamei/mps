
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import cn.damei.common.persistence.DataEntity;


public class BizOrderComplaintProblem extends DataEntity<BizOrderComplaintProblem> {

	private static final long serialVersionUID = 1L;
	private String orderComplaintId;
	private String complaintProblemId;
	private String complaintProblemTypeId;
	private String complaintProblemnei;
	private String complaintProblemNr;
	private String complaintProblemName;
	private String taskPackageTemplatId;
	private String orderTaskpackageId;
	private String complaintRoleType;
	private String complaintProblemDescribe;
	private String status;
	private String complaintProblemItemIds;
	private String ciIds;
	private String ordersarea;
	private String complaintProblemItemPics;
	private BizComplaintProblemItem bizComplaintProblemItemBean = new BizComplaintProblemItem();
	private List<BizComplaintProblemItem> bizComplaintProblemItemList = new ArrayList<BizComplaintProblemItem>();

	private String complaintId;
	private String orderid;
	private String ordernumber;
	private String itemmanagerid;
	private String itemmanager;
	private String itemmanagerIphone;
	private String orderinspectorid;
	private String orderinspector;
	private String orderinspectoriphone;
	private String customername;
	private String packageid;
	private String packagename;
	private String templatid;
	private String templatname;
	private String groupid;
	private String groupaddress;
	private String emgrouprelationid;
	private String emgrouprelationgroupId;
	private String emgrouprelationgroupempId;
	private String empId;
	private String empName;
	private String empphone;
	private String empids;
	private String custaddress;
	private MultipartFile[] photo;
	private String photos;

	public BizOrderComplaintProblem() {
		super();
	}

	public MultipartFile[] getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile[] photo) {
		this.photo = photo;
	}

	public BizOrderComplaintProblem(String id) {
		super(id);
	}

	public String getComplaintProblemnei() {
		return complaintProblemnei;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getCustaddress() {
		return custaddress;
	}

	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}

	public String getCiIds() {
		return ciIds;
	}

	public void setCiIds(String ciIds) {
		this.ciIds = ciIds;
	}

	public void setComplaintProblemnei(String complaintProblemnei) {
		this.complaintProblemnei = complaintProblemnei;
	}

	public String getComplaintProblemNr() {
		return complaintProblemNr;
	}

	public void setComplaintProblemNr(String complaintProblemNr) {
		this.complaintProblemNr = complaintProblemNr;
	}

	public String getEmpids() {
		return empids;
	}

	public void setEmpids(String empids) {
		this.empids = empids;
	}

	public String getOrdersarea() {
		return ordersarea;
	}

	public void setOrdersarea(String ordersarea) {
		this.ordersarea = ordersarea;
	}

	@Length(min = 0, max = 11, message = "工程信息ID长度必须介于 0 和 11 之间")
	public String getOrderComplaintId() {
		return orderComplaintId;
	}

	public String getComplaintProblemItemIds() {
		return complaintProblemItemIds;
	}

	public void setComplaintProblemItemIds(String complaintProblemItemIds) {
		this.complaintProblemItemIds = complaintProblemItemIds;
	}

	public String getComplaintProblemItemPics() {
		return complaintProblemItemPics;
	}

	public void setComplaintProblemItemPics(String complaintProblemItemPics) {
		this.complaintProblemItemPics = complaintProblemItemPics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BizComplaintProblemItem getBizComplaintProblemItemBean() {
		return bizComplaintProblemItemBean;
	}

	public void setBizComplaintProblemItemBean(BizComplaintProblemItem bizComplaintProblemItemBean) {
		this.bizComplaintProblemItemBean = bizComplaintProblemItemBean;
	}

	public String getComplaintProblemName() {
		return complaintProblemName;
	}

	public void setComplaintProblemName(String complaintProblemName) {
		this.complaintProblemName = complaintProblemName;
	}

	public String getComplaintProblemId() {
		return complaintProblemId;
	}

	public void setComplaintProblemId(String complaintProblemId) {
		this.complaintProblemId = complaintProblemId;
	}

	public String getItemmanagerIphone() {
		return itemmanagerIphone;
	}

	public void setItemmanagerIphone(String itemmanagerIphone) {
		this.itemmanagerIphone = itemmanagerIphone;
	}

	public String getOrderinspectoriphone() {
		return orderinspectoriphone;
	}

	public void setOrderinspectoriphone(String orderinspectoriphone) {
		this.orderinspectoriphone = orderinspectoriphone;
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getItemmanagerid() {
		return itemmanagerid;
	}

	public void setItemmanagerid(String itemmanagerid) {
		this.itemmanagerid = itemmanagerid;
	}

	public String getItemmanager() {
		return itemmanager;
	}

	public void setItemmanager(String itemmanager) {
		this.itemmanager = itemmanager;
	}

	public String getOrderinspectorid() {
		return orderinspectorid;
	}

	public void setOrderinspectorid(String orderinspectorid) {
		this.orderinspectorid = orderinspectorid;
	}

	public String getOrderinspector() {
		return orderinspector;
	}

	public void setOrderinspector(String orderinspector) {
		this.orderinspector = orderinspector;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getPackageid() {
		return packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getTemplatid() {
		return templatid;
	}

	public void setTemplatid(String templatid) {
		this.templatid = templatid;
	}

	public String getTemplatname() {
		return templatname;
	}

	public void setTemplatname(String templatname) {
		this.templatname = templatname;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupaddress() {
		return groupaddress;
	}

	public void setGroupaddress(String groupaddress) {
		this.groupaddress = groupaddress;
	}

	public String getEmgrouprelationid() {
		return emgrouprelationid;
	}

	public void setEmgrouprelationid(String emgrouprelationid) {
		this.emgrouprelationid = emgrouprelationid;
	}

	public String getEmgrouprelationgroupId() {
		return emgrouprelationgroupId;
	}

	public void setEmgrouprelationgroupId(String emgrouprelationgroupId) {
		this.emgrouprelationgroupId = emgrouprelationgroupId;
	}

	public String getEmgrouprelationgroupempId() {
		return emgrouprelationgroupempId;
	}

	public void setEmgrouprelationgroupempId(String emgrouprelationgroupempId) {
		this.emgrouprelationgroupempId = emgrouprelationgroupempId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpphone() {
		return empphone;
	}

	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}

	public void setOrderComplaintId(String orderComplaintId) {
		this.orderComplaintId = orderComplaintId;
	}

	public List<BizComplaintProblemItem> getBizComplaintProblemItemList() {
		return bizComplaintProblemItemList;
	}

	public void setBizComplaintProblemItemList(List<BizComplaintProblemItem> bizComplaintProblemItemList) {
		this.bizComplaintProblemItemList = bizComplaintProblemItemList;
	}

	@Length(min = 0, max = 11, message = "问题分类ID长度必须介于 0 和 11 之间")
	public String getComplaintProblemTypeId() {
		return complaintProblemTypeId;
	}

	public void setComplaintProblemTypeId(String complaintProblemTypeId) {
		this.complaintProblemTypeId = complaintProblemTypeId;
	}

	@Length(min = 0, max = 11, message = "任务包ID长度必须介于 0 和 11 之间")
	public String getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(String taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	@Length(min = 0, max = 11, message = "订单任务包ID长度必须介于 0 和 11 之间")
	public String getOrderTaskpackageId() {
		return orderTaskpackageId;
	}

	public void setOrderTaskpackageId(String orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}

	@Length(min = 0, max = 10, message = "被投诉对象长度必须介于 0 和 10 之间")
	public String getComplaintRoleType() {
		return complaintRoleType;
	}

	public void setComplaintRoleType(String complaintRoleType) {
		this.complaintRoleType = complaintRoleType;
	}

	@Length(min = 0, max = 255, message = "投诉问题描述长度必须介于 0 和 255 之间")
	public String getComplaintProblemDescribe() {
		return complaintProblemDescribe;
	}

	public void setComplaintProblemDescribe(String complaintProblemDescribe) {
		this.complaintProblemDescribe = complaintProblemDescribe;
	}

	@Length(min = 0, max = 10, message = "状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}