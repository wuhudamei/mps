/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 客户回访记录Entity
 * @author lft
 * @version 2017-05-26
 */
public class BizCustomerReturnVisitRecord extends DataEntity2<BizCustomerReturnVisitRecord> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单ID
	private String customServiceId;		// 回访人员ID
	private String customServiceName;		// 回访人员姓名
	private Date returnVisitTime;		// 回访时间
	private String returnVisitNode;		// 回访节点id
	private String returnVisitNodeName;		// 回访节点名称
	private String storeId; //门店ID
	private String storeName; //门店名字
	private String projectModeName; //工程模式名字
	private String projectMode; //工程模式
	private String area; //区域
	private String areaName; //区域名字
	private String orderNumber; //订单编号
	private String customerAddress;//工程地址
	private String customerPhone; //客户电话
	private String customerName; //客户名字
	private String designerName;//设计师的名字
	private String itemManager;//项目经理
	private String orderInspector; //质检名字
	private Date contractStartDate;//合同开工日期
	private Date contractEndDate;	//合同竣工日期
	private Date actualStartDate;//实际开工日期
	private Date nodeCheckDate;	//节点验收时间
	private Date nodeCheckDateBegin;	//节点验收时间区间开始
	private Date nodeCheckDateEnd;	//节点验收时间区间结束
	private Date visitDate; //回访日期
	private Date visitDateBegin; //回访开始日期
	private Date visitDateEnd; //回访结束日期
	private String orderInspectorPhone;//质检电话
	private String itemManagerPhone;//项目经理电话
	private String designerPhone;//设计师电话
	private List<BizCustomerReturnVisitRecordAnswer> answerList; //问题和答案
	private Integer enabled;
	private Integer invalidNum;   //回访次数
	private Integer isValid;  //是否有效
	private String invalidReason; //无效原因
	private Date invalidDateBegin;   //无效起始日期
	private Date invalidDateEnd;	//无效结束日期
	private Date invalid;		//无效回访时间

	private String num;
	private String size;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getInvalid() {
		return invalid;
	}

	public void setInvalid(Date invalid) {
		this.invalid = invalid;
	}

	public String getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}


	public Date getInvalidDateBegin() {
		return invalidDateBegin;
	}

	public void setInvalidDateBegin(Date invalidDateBegin) {
		this.invalidDateBegin = invalidDateBegin;
	}

	public Date getInvalidDateEnd() {
		return invalidDateEnd;
	}

	public void setInvalidDateEnd(Date invalidDateEnd) {
		this.invalidDateEnd = invalidDateEnd;
	}

	public List<BizCustomerReturnVisitRecordAnswer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<BizCustomerReturnVisitRecordAnswer> answerList) {
		this.answerList = answerList;
	}


	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}

	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}

	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getVisitDateBegin() {
		return visitDateBegin;
	}

	public void setVisitDateBegin(Date visitDateBegin) {
		this.visitDateBegin = visitDateBegin;
	}

	public Date getVisitDateEnd() {
		return visitDateEnd;
	}

	public void setVisitDateEnd(Date visitDateEnd) {
		this.visitDateEnd = visitDateEnd;
	}

	public BizCustomerReturnVisitRecord() {
		super();
	}

	public BizCustomerReturnVisitRecord(Integer id){
		super(id);
	}

	@NotNull(message="订单ID不能为空")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=1, max=50, message="回访人员ID长度必须介于 1 和 50 之间")
	public String getCustomServiceId() {
		return customServiceId;
	}

	public void setCustomServiceId(String customServiceId) {
		this.customServiceId = customServiceId;
	}
	
	@Length(min=1, max=50, message="回访人员姓名长度必须介于 1 和 50 之间")
	public String getCustomServiceName() {
		return customServiceName;
	}

	public void setCustomServiceName(String customServiceName) {
		this.customServiceName = customServiceName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="回访时间不能为空")
	public Date getReturnVisitTime() {
		return returnVisitTime;
	}

	public void setReturnVisitTime(Date returnVisitTime) {
		this.returnVisitTime = returnVisitTime;
	}
	public String getReturnVisitNode() {
		return returnVisitNode;
	}

	public void setReturnVisitNode(String returnVisitNode) {
		this.returnVisitNode = returnVisitNode;
	}

	@Length(min=1, max=50, message="回访节点名称长度必须介于 1 和 50 之间")
	public String returnVisitTime() {
		return returnVisitNodeName;
	}

	public String getReturnVisitNodeName() {
		return returnVisitNodeName;
	}

	public void setReturnVisitNodeName(String returnVisitNodeName) {
		this.returnVisitNodeName = returnVisitNodeName;
	}

	public Date getNodeCheckDate() {
		return nodeCheckDate;
	}

	public void setNodeCheckDate(Date nodeCheckDate) {
		this.nodeCheckDate = nodeCheckDate;
	}

	public Date getNodeCheckDateBegin() {
		return nodeCheckDateBegin;
	}

	public void setNodeCheckDateBegin(Date nodeCheckDateBegin) {
		this.nodeCheckDateBegin = nodeCheckDateBegin;
	}

	public Date getNodeCheckDateEnd() {
		return nodeCheckDateEnd;
	}

	public void setNodeCheckDateEnd(Date nodeCheckDateEnd) {
		this.nodeCheckDateEnd = nodeCheckDateEnd;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Integer getInvalidNum() {
		return invalidNum;
	}

	public void setInvalidNum(Integer invalidNum) {
		this.invalidNum = invalidNum;
	}
	
}