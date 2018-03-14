/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程问题Entity
 * 
 * @author ztw
 * @version 2017-07-06
 */
public class BizOrderComplaintProblem extends DataEntity<BizOrderComplaintProblem> {

	private static final long serialVersionUID = 1L;
	private String orderComplaintId; // 投訴訂單ID
	private String complaintProblemId; // 投訴問題ID
	private String complaintProblemTypeId; // 问题分类ID
	private String complaintProblemnei; // 问题内容
	private String complaintProblemNr; // 第二个问题内容字段
	private String complaintProblemName; // 问题分类名稱
	private String taskPackageTemplatId; // 任务包模板ID
	private String orderTaskpackageId; // 订单任务包ID
	private String complaintRoleType; // 被投诉对象
	private String complaintProblemDescribe; // 投诉问题描述
	private String status; // 状态
	private String complaintProblemItemIds; // 问题事项串
	private String ciIds; // 问题事项串
	private String ordersarea; // 问题事项串
	private String complaintProblemItemPics; // 图片urlS
	private BizComplaintProblemItem bizComplaintProblemItemBean = new BizComplaintProblemItem(); // 问题事项集合
	private List<BizComplaintProblemItem> bizComplaintProblemItemList = new ArrayList<BizComplaintProblemItem>(); // 问题事项集合

	private String complaintId; // 投诉订单ID
	private String orderid; // 订单ID
	private String ordernumber; // 订单编号
	private String itemmanagerid; // 项目经理ID
	private String itemmanager; // 项目经理名字
	private String itemmanagerIphone; // 项目经理手机号
	private String orderinspectorid; // 质检员id
	private String orderinspector; // 质检员姓名
	private String orderinspectoriphone; // 质检员手机号
	private String customername; // 客户姓名
	private String packageid; // 任务包ID
	private String packagename; // 任务包名称
	private String templatid; // 任务包di
	private String templatname; // 任务包名称
	private String groupid; // 人员组di
	private String groupaddress; // 人员组地址
	private String emgrouprelationid; // 人员组对照表id
	private String emgrouprelationgroupId; // 人员组中的工人组ID
	private String emgrouprelationgroupempId; // 人员组中的工人ID
	private String empId; // 工人ID
	private String empName; // 工人姓名
	private String empphone; // 工人手机
	private String empids; // 工人手机
	private String custaddress; // 工人手机
	private MultipartFile[] photo; // 上传图片类
	private String photos; // 售后带过来的图片地址

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