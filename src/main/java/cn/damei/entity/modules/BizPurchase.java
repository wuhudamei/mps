
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizPurchase extends DataEntity2<BizPurchase> {
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;
	private String purchaseCode;
	private String purchaseType;
	private String receiverName;
	private String receiverPhone;
	private Date applyReceiveTime;
	private Integer applyEmployee;
	private Date applyTime;
	private String status;
	private Double totalPrice;
	private Date beginApplyReceiveTime;
	private Date endApplyReceiveTime;
	private Date beginApplyTime;
	private Date endApplyTime;
	private Date transferSupplierDatetime;
	private Date receiveAllGoodsDatetime;
	private String statusDescribe;
	
	
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