/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 选材变更单表Entity
 * @author wyb
 * @version 2017-06-14
 */
public class BizMaterialsChoiceChangeBill extends DataEntity2<BizMaterialsChoiceChangeBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private String orderNumber;		// 订单编号
	private String changeBillCode;		// 变更单编号
	private String changeReason;		// 变更原因
	private Date changeApplyDate;		// 设计师申请变更原因
	private String designerName;		// 设计师姓名
	private Date changeCheckedDate;		// 变更单审核通过日期
	private String checkerName;		// 审计员姓名
	private Double addItemTotalAmount;		// 增项合计金额
	private Double reduceItemTotalAmount;		// 减项合计金额
	private Double changeBillTotalAmount;		// 变更单总金额
	
	private Integer storeId;		// 门店id
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户手机号
	private String communityName;		// 小区
	private String buildNumber;		// 楼
	private String buildUnit;		// 单元
	private String buildRoom;		// 室
	private String orderDesignerName;		// 订单设计师
	private String orderDesignerPhone;		// 订单设计师电话
	private String itemManager;		// 项目经理姓名
	private String storeName;		// 门店名称
	private String contractNumber;		// 合同编号
	
	
	public BizMaterialsChoiceChangeBill() {
		super();
	}

	public BizMaterialsChoiceChangeBill(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=100, message="订单编号长度必须介于 0 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Length(min=0, max=100, message="变更单编号长度必须介于 0 和 100 之间")
	public String getChangeBillCode() {
		return changeBillCode;
	}

	public void setChangeBillCode(String changeBillCode) {
		this.changeBillCode = changeBillCode;
	}
	
	@Length(min=0, max=255, message="变更原因长度必须介于 0 和 255 之间")
	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChangeApplyDate() {
		return changeApplyDate;
	}

	public void setChangeApplyDate(Date changeApplyDate) {
		this.changeApplyDate = changeApplyDate;
	}
	
	@Length(min=0, max=20, message="设计师姓名长度必须介于 0 和 20 之间")
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChangeCheckedDate() {
		return changeCheckedDate;
	}

	public void setChangeCheckedDate(Date changeCheckedDate) {
		this.changeCheckedDate = changeCheckedDate;
	}
	
	@Length(min=0, max=20, message="审计员姓名长度必须介于 0 和 20 之间")
	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	
	public Double getAddItemTotalAmount() {
		return addItemTotalAmount;
	}

	public void setAddItemTotalAmount(Double addItemTotalAmount) {
		this.addItemTotalAmount = addItemTotalAmount;
	}
	
	public Double getReduceItemTotalAmount() {
		return reduceItemTotalAmount;
	}

	public void setReduceItemTotalAmount(Double reduceItemTotalAmount) {
		this.reduceItemTotalAmount = reduceItemTotalAmount;
	}
	
	public Double getChangeBillTotalAmount() {
		return changeBillTotalAmount;
	}

	public void setChangeBillTotalAmount(Double changeBillTotalAmount) {
		this.changeBillTotalAmount = changeBillTotalAmount;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	public String getOrderDesignerName() {
		return orderDesignerName;
	}

	public void setOrderDesignerName(String orderDesignerName) {
		this.orderDesignerName = orderDesignerName;
	}

	public String getOrderDesignerPhone() {
		return orderDesignerPhone;
	}

	public void setOrderDesignerPhone(String orderDesignerPhone) {
		this.orderDesignerPhone = orderDesignerPhone;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
}