/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理签到查询Entity
 * @author 梅浩
 * @version 2016-09-26
 */
public class ManagerSign extends DataEntity2<ManagerSign> {
	
	private static final long serialVersionUID = 1L;
	
	
	private String orderNumber;//订单编号
	private String orderProjectMode;//;订单相关工程模式
	public String getOrderProjectMode() {
		return orderProjectMode;
	}

	public void setOrderProjectMode(String orderProjectMode) {
		this.orderProjectMode = orderProjectMode;
	}

	private Integer storeId; //门店id
	private String storeName;//门店名称
	private Integer orderId;		// 相关订单id
	private String signPic;		// 签到照片
	private Date signDate;		// 签到日期,时分秒
	private String signAddress;		// 签到地址
	private Double signDistance;		// 误差(单位:米)
	private Integer managerId;		// 项目经理id
	private String managerName;		// 经理姓名
	private String customerInfo;		// 顾客信息
	private String customerName;     //顾客姓名
	private Double conditionDistance1;//条件距离1
	private Double conditionDistance2;//条件距s离2
	private Date  signDate1;//签到时间1
	private Date signDate2;//签到时间2
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