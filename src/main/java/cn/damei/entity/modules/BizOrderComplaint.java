
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizOrderComplaint extends DataEntity<BizOrderComplaint> {

	private static final long serialVersionUID = 1L;
	private String orderId;
	private String complaintSource;
	private String dataInputChannel;
	private String cusServiceProblemId;
	private String complaintPersonName;
	private String complaintPersonPhone;
	private String status;
	private String storeId;
	private String typeName;
	private String orderNumber;
	private String projectMode;
	private String acceptArea;
	private String customerAddress;
	private String customerName;
	private String customerPhone;
	private String itemManager;
	private String itemManagerIphnoe;
	private String comcreateDate;
	private Date comcreateDateNstring;
	private Order order = new Order();
	private String tsNmae;
	private String biItembeans;
	private String dataInput;
	private String afterId;
	private String complaintProblemnei;
	private String complaintProblemNr;
	private BizComplaintProblemType bizComplaintProblemType = new BizComplaintProblemType();
	private BizOrderComplaintProblem bizOrderComplaintProblem = new BizOrderComplaintProblem();
	private String bizComplaintProblemTypeID;
	private List<BizComplaintProblemItem> biItemList = new ArrayList<BizComplaintProblemItem>();
	private String pqcName;
	private String detailAddress;
	private String cusServiceId;
	private String workOrderCode;

	private String departName;
	private String businesstype;
	private Integer businessid;



	private Double pointx;
	private Double pointy;

	public Double getPointx() {
		return pointx;
	}

	public Date getComcreateDateNstring() {
		return comcreateDateNstring;
	}

	public void setComcreateDateNstring(Date comcreateDateNstring) {
		this.comcreateDateNstring = comcreateDateNstring;
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx;
	}

	public Double getPointy() {
		return pointy;
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy;
	}

	public String getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public String getCusServiceId() {
		return cusServiceId;
	}

	public void setCusServiceId(String cusServiceId) {
		this.cusServiceId = cusServiceId;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public String getComplaintProblemNr() {
		return complaintProblemNr;
	}

	public void setComplaintProblemNr(String complaintProblemNr) {
		this.complaintProblemNr = complaintProblemNr;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getPqcName() {
		return pqcName;
	}

	public void setPqcName(String pqcName) {
		this.pqcName = pqcName;
	}



	private List<BizOrderComplaintProblem> bizOrderComplaintProblemList = new ArrayList<BizOrderComplaintProblem>();

	private String tsiphone;
	private String tsnr;
	private String complaintId;

	public BizOrderComplaint() {
		super();
	}

	public String getComplaintProblemnei() {
		return complaintProblemnei;
	}

	public void setComplaintProblemnei(String complaintProblemnei) {
		this.complaintProblemnei = complaintProblemnei;
	}

	public BizOrderComplaintProblem getBizOrderComplaintProblem() {
		return bizOrderComplaintProblem;
	}

	public void setBizOrderComplaintProblem(BizOrderComplaintProblem bizOrderComplaintProblem) {
		this.bizOrderComplaintProblem = bizOrderComplaintProblem;
	}

	public String getDataInput() {
		return dataInput;
	}

	public void setDataInput(String dataInput) {
		this.dataInput = dataInput;
	}

	public String getAfterId() {
		return afterId;
	}

	public void setAfterId(String afterId) {
		this.afterId = afterId;
	}

	public List<BizOrderComplaintProblem> getBizOrderComplaintProblemList() {
		return bizOrderComplaintProblemList;
	}

	public void setBizOrderComplaintProblemList(List<BizOrderComplaintProblem> bizOrderComplaintProblemList) {
		this.bizOrderComplaintProblemList = bizOrderComplaintProblemList;
	}

	public String getBizComplaintProblemTypeID() {
		return bizComplaintProblemTypeID;
	}

	public String getTsnr() {
		return tsnr;
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public void setTsnr(String tsnr) {
		this.tsnr = tsnr;
	}

	public void setBizComplaintProblemTypeID(String bizComplaintProblemTypeID) {
		this.bizComplaintProblemTypeID = bizComplaintProblemTypeID;
	}

	public List<BizComplaintProblemItem> getBiItemList() {
		return biItemList;
	}

	public void setBiItemList(List<BizComplaintProblemItem> biItemList) {
		this.biItemList = biItemList;
	}

	public String getBiItembeans() {
		return biItembeans;
	}

	public void setBiItembeans(String biItembeans) {
		this.biItembeans = biItembeans;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getAcceptArea() {
		return acceptArea;
	}

	public void setAcceptArea(String acceptArea) {
		this.acceptArea = acceptArea;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerIphnoe() {
		return itemManagerIphnoe;
	}

	public void setItemManagerIphnoe(String itemManagerIphnoe) {
		this.itemManagerIphnoe = itemManagerIphnoe;
	}

	public String getComcreateDate() {
		return comcreateDate;
	}

	public void setComcreateDate(String comcreateDate) {
		this.comcreateDate = comcreateDate;
	}

	public BizOrderComplaint(String id) {
		super(id);
	}

	public String getStoreId() {
		return storeId;
	}

	public BizComplaintProblemType getBizComplaintProblemType() {
		return bizComplaintProblemType;
	}

	public void setBizComplaintProblemType(BizComplaintProblemType bizComplaintProblemType) {
		this.bizComplaintProblemType = bizComplaintProblemType;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Length(min = 0, max = 11, message = "订单ID长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public String getTsNmae() {
		return tsNmae;
	}

	public void setTsNmae(String tsNmae) {
		this.tsNmae = tsNmae;
	}

	public String getTsiphone() {
		return tsiphone;
	}

	public void setTsiphone(String tsiphone) {
		this.tsiphone = tsiphone;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 10, message = "投诉来源长度必须介于 0 和 10 之间")
	public String getComplaintSource() {
		return complaintSource;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setComplaintSource(String complaintSource) {
		this.complaintSource = complaintSource;
	}

	@Length(min = 0, max = 10, message = "数据录入渠道长度必须介于 0 和 10 之间")
	public String getDataInputChannel() {
		return dataInputChannel;
	}

	public void setDataInputChannel(String dataInputChannel) {
		this.dataInputChannel = dataInputChannel;
	}

	@Length(min = 0, max = 11, message = "售后问题id长度必须介于 0 和 11 之间")
	public String getCusServiceProblemId() {
		return cusServiceProblemId;
	}

	public void setCusServiceProblemId(String cusServiceProblemId) {
		this.cusServiceProblemId = cusServiceProblemId;
	}

	@Length(min = 0, max = 20, message = "投诉人姓名长度必须介于 0 和 20 之间")
	public String getComplaintPersonName() {
		return complaintPersonName;
	}

	public void setComplaintPersonName(String complaintPersonName) {
		this.complaintPersonName = complaintPersonName;
	}

	@Length(min = 0, max = 11, message = "投诉人手机号长度必须介于 0 和 11 之间")
	public String getComplaintPersonPhone() {
		return complaintPersonPhone;
	}

	public void setComplaintPersonPhone(String complaintPersonPhone) {
		this.complaintPersonPhone = complaintPersonPhone;
	}

	@Length(min = 0, max = 10, message = "状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}