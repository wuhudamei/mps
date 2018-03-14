/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 采购单
 * @author wang
 * @version 2016-09-28
 */
public class BizPurchase extends DataEntity2<BizPurchase> {
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;		// 订单id -- '
	private String purchaseCode;		// 采购单编码 -- '
	private String purchaseType;		// 采购单类型 -- '辅材，主材
	private String receiverName;		// 收货人 -- '
	private String receiverPhone;		// 收货电话 -- '
	private Date applyReceiveTime;		// 期望送货日期 -- '
	private Integer applyEmployee;		// 申请人 -- '
	private Date applyTime;		// 申请时间 -- '
	private String status;		// 状态 -- '
	private Double totalPrice;		// 总价格 -- '
	private Date beginApplyReceiveTime;		// 开始 期望送货日期 -- '
	private Date endApplyReceiveTime;		// 结束 期望送货日期 -- '
	private Date beginApplyTime;		// 开始 申请时间 -- '
	private Date endApplyTime;		// 结束 申请时间 -- '
	private Date transferSupplierDatetime; //转给供应商时间
	private Date receiveAllGoodsDatetime;//收货完成日期
	private String statusDescribe; //废弃原因
	
	
	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public BizPurchase() {
		super();
	}

	public BizPurchase(Integer id){
		super(id);
	}

	@NotNull(message="订单id -- '不能为空")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=64, message="采购单编码 -- '长度必须介于 0 和 64 之间")
	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	
	@Length(min=0, max=10, message="采购单类型 -- '辅材，主材长度必须介于 0 和 10 之间")
	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	@Length(min=0, max=30, message="收货人 -- '长度必须介于 0 和 30 之间")
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@Length(min=0, max=11, message="收货电话 -- '长度必须介于 0 和 11 之间")
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyReceiveTime() {
		return applyReceiveTime;
	}

	public void setApplyReceiveTime(Date applyReceiveTime) {
		this.applyReceiveTime = applyReceiveTime;
	}
	
	public Integer getApplyEmployee() {
		return applyEmployee;
	}

	public void setApplyEmployee(Integer applyEmployee) {
		this.applyEmployee = applyEmployee;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Length(min=0, max=10, message="状态 -- '长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Date getBeginApplyReceiveTime() {
		return beginApplyReceiveTime;
	}

	public void setBeginApplyReceiveTime(Date beginApplyReceiveTime) {
		this.beginApplyReceiveTime = beginApplyReceiveTime;
	}
	
	public Date getEndApplyReceiveTime() {
		return endApplyReceiveTime;
	}

	public void setEndApplyReceiveTime(Date endApplyReceiveTime) {
		this.endApplyReceiveTime = endApplyReceiveTime;
	}
		
	public Date getBeginApplyTime() {
		return beginApplyTime;
	}

	public void setBeginApplyTime(Date beginApplyTime) {
		this.beginApplyTime = beginApplyTime;
	}
	
	public Date getEndApplyTime() {
		return endApplyTime;
	}

	public void setEndApplyTime(Date endApplyTime) {
		this.endApplyTime = endApplyTime;
	}

	public Date getTransferSupplierDatetime() {
		return transferSupplierDatetime;
	}

	public void setTransferSupplierDatetime(Date transferSupplierDatetime) {
		this.transferSupplierDatetime = transferSupplierDatetime;
	}

	public Date getReceiveAllGoodsDatetime() {
		return receiveAllGoodsDatetime;
	}

	public void setReceiveAllGoodsDatetime(Date receiveAllGoodsDatetime) {
		this.receiveAllGoodsDatetime = receiveAllGoodsDatetime;
	}
	
}