
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class ManagerSign extends DataEntity2<ManagerSign> {
	
	private static final long serialVersionUID = 1L;
	
	
	private String orderNumber;
	private String orderProjectMode;
	public String getOrderProjectMode() {
		return orderProjectMode;
	}

	public void setOrderProjectMode(String orderProjectMode) {
		this.orderProjectMode = orderProjectMode;
	}

	private Integer storeId;
	private String storeName;
	private Integer orderId;
	private String signPic;
	private Date signDate;
	private String signAddress;
	private Double signDistance;
	private Integer managerId;
	private String managerName;
	private String customerInfo;
	private String customerName;
	private Double conditionDistance1;
	private Double conditionDistance2;
	private Date  signDate1;
	private Date signDate2;
	private String isValid; 
	private Integer engineDepartId;
	private Integer signId;

	public Integer getSignId() {
		return signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getSignDate1() {
		return signDate1;
	}

	public void setSignDate1(Date signDate1) {
		this.signDate1 = signDate1;
	}

	public Date getSignDate2() {
		return signDate2;
	}

	public void setSignDate2(Date signDate2) {
		this.signDate2 = signDate2;
	}

	public Double getConditionDistance1() {
		return conditionDistance1;
	}

	public void setConditionDistance1(Double conditionDistance1) {
		this.conditionDistance1 = conditionDistance1;
	}

	public Double getConditionDistance2() {
		return conditionDistance2;
	}

	public void setConditionDistance2(Double conditionDistance2) {
		this.conditionDistance2 = conditionDistance2;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public ManagerSign() {
		super();
	}

	public ManagerSign(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	

	
	@Length(min=0, max=1000, message="签到照片长度必须介于 0 和 1000 之间")
	public String getSignPic() {
		return signPic;
	}

	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
	@Length(min=0, max=255, message="签到地址长度必须介于 0 和 255 之间")
	public String getSignAddress() {
		return signAddress;
	}

	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
	
	
	
	@Length(min=0, max=100, message="项目经理id长度必须介于 0 和 100 之间")
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	@Length(min=0, max=100, message="经理姓名长度必须介于 0 和 100 之间")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	
	public Double getSignDistance() {
		return signDistance;
	}

	public void setSignDistance(Double signDistance) {
		this.signDistance = signDistance;
	}

	@Length(min=0, max=255, message="顾客信息长度必须介于 0 和 255 之间")
	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	
}