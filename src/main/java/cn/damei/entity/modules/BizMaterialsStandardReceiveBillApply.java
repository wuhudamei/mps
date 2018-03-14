
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandardReceiveBillApply extends DataEntity2<BizMaterialsStandardReceiveBillApply> {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String orderNumber;
	private String materialsStandardReceiveBillCode;
	private Date receiveDatetime;
	private String itemManager;
	private Double receiveBillAmount;
	private String isSettled;
	private Date applyDatetime;
	private Integer status = 10;
	private String customerName;

	private Date fristApplyDatetime;
	private Date endApplyDatetime;

	private Date fristReceiveDatetime;
	private Date endReceiveDatetime;

	private Integer operatorEmployeeId;
	private Date operateDatetime;
	private String abandonReason;
	private String receiveBillType;
	private Integer shippingType;
	private Integer shippingFee;
	private String isScrap;

	public Integer getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Integer shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Integer getShippingType() {
		return shippingType;
	}

	public void setShippingType(Integer shippingType) {
		this.shippingType = shippingType;
	}

	public String getReceiveBillType() {
		return receiveBillType;
	}

	public void setReceiveBillType(String receiveBillType) {
		this.receiveBillType = receiveBillType;
	}

	public String getAbandonReason() {
		return abandonReason;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public void setAbandonReason(String abandonReason) {
		this.abandonReason = abandonReason;
	}

	private String realName;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public BizMaterialsStandardReceiveBillApply(Integer id) {
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

	@Length(min = 0, max = 100, message = "领取单号长度必须介于 0 和 100 之间")
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