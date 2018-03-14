
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsChoiceChangeBill extends DataEntity2<BizMaterialsChoiceChangeBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String orderNumber;
	private String changeBillCode;
	private String changeReason;
	private Date changeApplyDate;
	private String designerName;
	private Date changeCheckedDate;
	private String checkerName;
	private Double addItemTotalAmount;
	private Double reduceItemTotalAmount;
	private Double changeBillTotalAmount;
	
	private Integer storeId;
	private String customerName;
	private String customerPhone;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String orderDesignerName;
	private String orderDesignerPhone;
	private String itemManager;
	private String storeName;
	private String contractNumber;
	
	
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