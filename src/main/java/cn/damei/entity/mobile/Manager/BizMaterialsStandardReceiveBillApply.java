
package cn.damei.entity.mobile.Manager;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandardReceiveBillApply extends DataEntity2<BizMaterialsStandardReceiveBillApply> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;
	private String name;
	private String orderNumber;
	private String materialsStandardReceiveBillCode;
	private Date receiveDatetime;
	private String itemManager;
	private Double receiveBillAmount;
	private String isSettled;
	private Date applyDatetime;
	private Integer status;
	private String customerName;
	private Integer applyEmployeeId;
	private Integer storeId;
	
	private Date fristApplyDatetime;
	private Date endApplyDatetime;
	
	private Date fristReceiveDatetime;
	private Date endReceiveDatetime;
	
	private Integer operatorEmployeeId;
	private Date operateDatetime;
	private String abandonReason;
	private String isView;
	private String viewDatetime;
	private String receiveBillType;
	private Integer shippingType;
	private Double shippingFee;

	public Integer getShippingType() {
		return shippingType;
	}

	public void setShippingType(Integer shippingType) {
		this.shippingType = shippingType;
	}

	public Double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getReceiveBillType() {
		return receiveBillType;
	}

	public void setReceiveBillType(String receiveBillType) {
		this.receiveBillType = receiveBillType;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public String getViewDatetime() {
		return viewDatetime;
	}

	public void setViewDatetime(String viewDatetime) {
		this.viewDatetime = viewDatetime;
	}

	public String getAbandonReason() {
		return abandonReason;
	}

	public void setAbandonReason(String abandonReason) {
		this.abandonReason = abandonReason;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}

	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOperatorEmployeeId() {
		return operatorEmployeeId;
	}

	public void setOperatorEmployeeId(Integer operatorEmployeeId) {
		this.operatorEmployeeId = operatorEmployeeId;
	}

	public Date getOperateDatetime() {
		return operateDatetime;
	}

	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFristApplyDatetime() {
		return fristApplyDatetime;
	}

	public void setFristApplyDatetime(Date fristApplyDatetime) {
		this.fristApplyDatetime = fristApplyDatetime;
	}

	public Date getEndApplyDatetime() {
		return endApplyDatetime;
	}

	public void setEndApplyDatetime(Date endApplyDatetime) {
		this.endApplyDatetime = endApplyDatetime;
	}

	public Date getFristReceiveDatetime() {
		return fristReceiveDatetime;
	}

	public void setFristReceiveDatetime(Date fristReceiveDatetime) {
		this.fristReceiveDatetime = fristReceiveDatetime;
	}

	public Date getEndReceiveDatetime() {
		return endReceiveDatetime;
	}

	public void setEndReceiveDatetime(Date endReceiveDatetime) {
		this.endReceiveDatetime = endReceiveDatetime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BizMaterialsStandardReceiveBillApply() {
		super();
	}

	public BizMaterialsStandardReceiveBillApply(Integer id){
		super(id);
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Length(min=0, max=100, message="领取单号长度必须介于 0 和 100 之间")
	public String getMaterialsStandardReceiveBillCode() {
		return materialsStandardReceiveBillCode;
	}

	public void setMaterialsStandardReceiveBillCode(String materialsStandardReceiveBillCode) {
		this.materialsStandardReceiveBillCode = materialsStandardReceiveBillCode;
	}
	
	public Date getApplyDatetime() {
		return applyDatetime;
	}

	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveDatetime() {
		return receiveDatetime;
	}

	public void setReceiveDatetime(Date receiveDatetime) {
		this.receiveDatetime = receiveDatetime;
	}
	
	
	
	

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Double getReceiveBillAmount() {
		return receiveBillAmount;
	}

	public void setReceiveBillAmount(Double receiveBillAmount) {
		this.receiveBillAmount = receiveBillAmount;
	}
	
	public String getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
		
}