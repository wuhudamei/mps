/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.mobile.Manager;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 标化辅材申请记录Entity
 * @author zkj
 * @version 2016-12-26
 */
public class BizMaterialsStandardReceiveBillApply extends DataEntity2<BizMaterialsStandardReceiveBillApply> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;//订单的id
	private String name;		// 门店name
	private String orderNumber;		// 订单
	private String materialsStandardReceiveBillCode;		// 领取单号
	private Date receiveDatetime;		// 领取日期
	private String itemManager;		// 领取人 ,项目经理
	private Double receiveBillAmount;		// 领取单总金额 -- '
	private String isSettled;		// 是否已作项目经理结算 -- '1.是；0.否
	private Date applyDatetime;		// 申请日期
	private Integer status;      //领取单的状态 10.项目经理已申请 30.已领取 20.已作废 默认为0
	private String customerName; //客户的名字
	private Integer applyEmployeeId;//申请人的ID
	private Integer storeId;//门店id
	
	private Date fristApplyDatetime;		// 开始申请日期
	private Date endApplyDatetime;		// 结束申请日期
	
	private Date fristReceiveDatetime;		// 开始领取日期
	private Date endReceiveDatetime;		// 结束领取日期
	
	private Integer operatorEmployeeId; //当前系统操作人
	private Date operateDatetime;//操作时间
	private String abandonReason;//废弃理由
	private String isView; //是否查看過
	private String viewDatetime; //查看时间
	private String receiveBillType;// 1 标化 2 筒灯
	private Integer shippingType;// 配送方式  0:自提 1:配送
	private Double shippingFee; //配送价格

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