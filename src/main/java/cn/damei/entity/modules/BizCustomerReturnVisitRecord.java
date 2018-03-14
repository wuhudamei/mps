
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizCustomerReturnVisitRecord extends DataEntity2<BizCustomerReturnVisitRecord> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String customServiceId;
	private String customServiceName;
	private Date returnVisitTime;
	private String returnVisitNode;
	private String returnVisitNodeName;
	private String storeId;
	private String storeName;
	private String projectModeName;
	private String projectMode;
	private String area;
	private String areaName;
	private String orderNumber;
	private String customerAddress;
	private String customerPhone;
	private String customerName;
	private String designerName;
	private String itemManager;
	private String orderInspector;
	private Date contractStartDate;
	private Date contractEndDate;
	private Date actualStartDate;
	private Date nodeCheckDate;
	private Date nodeCheckDateBegin;
	private Date nodeCheckDateEnd;
	private Date visitDate;
	private Date visitDateBegin;
	private Date visitDateEnd;
	private String orderInspectorPhone;
	private String itemManagerPhone;
	private String designerPhone;
	private List<BizCustomerReturnVisitRecordAnswer> answerList;
	private Integer enabled;
	private Integer invalidNum;
	private Integer isValid;
	private String invalidReason;
	private Date invalidDateBegin;
	private Date invalidDateEnd;
	private Date invalid;

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