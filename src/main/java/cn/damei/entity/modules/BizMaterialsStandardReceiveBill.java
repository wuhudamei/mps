/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 标化辅材记录Entity
 * @author 汪文文
 * @version 2016-12-26
 */
public class BizMaterialsStandardReceiveBill extends DataEntity2<BizMaterialsStandardReceiveBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id
	private Integer orderId;		// 订单id
	private String materialsStandardReceiveBillCode;		// 领取单号
	private Date receiveDatetime;		// 领取日期
	private Integer receiveEmployeeId;		// 领取人员工id -- '
	private Double receiveBillAmount;		// 领取单总金额 -- '
	private String isSettled;		// 是否已作项目经理结算 -- '1.是；0.否
	private Date beginReceiveDatetime;		// 开始 领取日期
	private Date endReceiveDatetime;		// 结束 领取日期
	
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