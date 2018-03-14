
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandardReceiveBill extends DataEntity2<BizMaterialsStandardReceiveBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer orderId;
	private String materialsStandardReceiveBillCode;
	private Date receiveDatetime;
	private Integer receiveEmployeeId;
	private Double receiveBillAmount;
	private String isSettled;
	private Date beginReceiveDatetime;
	private Date endReceiveDatetime;
	
	public BizMaterialsStandardReceiveBill() {
		super();
	}

	public BizMaterialsStandardReceiveBill(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=100, message="领取单号长度必须介于 0 和 100 之间")
	public String getMaterialsStandardReceiveBillCode() {
		return materialsStandardReceiveBillCode;
	}

	public void setMaterialsStandardReceiveBillCode(String materialsStandardReceiveBillCode) {
		this.materialsStandardReceiveBillCode = materialsStandardReceiveBillCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveDatetime() {
		return receiveDatetime;
	}

	public void setReceiveDatetime(Date receiveDatetime) {
		this.receiveDatetime = receiveDatetime;
	}
	
	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}

	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
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
	
	public Date getBeginReceiveDatetime() {
		return beginReceiveDatetime;
	}

	public void setBeginReceiveDatetime(Date beginReceiveDatetime) {
		this.beginReceiveDatetime = beginReceiveDatetime;
	}
	
	public Date getEndReceiveDatetime() {
		return endReceiveDatetime;
	}

	public void setEndReceiveDatetime(Date endReceiveDatetime) {
		this.endReceiveDatetime = endReceiveDatetime;
	}
		
}