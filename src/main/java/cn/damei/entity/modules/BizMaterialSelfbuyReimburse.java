
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialSelfbuyReimburse extends DataEntity2<BizMaterialSelfbuyReimburse> {
	
	private static final long serialVersionUID = 1L;
	private String reimburseType;
	private Integer relatedReimburseId;
	private Integer orderId;
	private Integer materialSelfbuyId;
	private Double customerPayAmount;
	private String settleStage;
	private Double settleAmount;
	private String reimburseRemarks;
	private String reimburseStatus;
	private Date reimburseStatusDatetime;
	private String reimburseStatusRemarks;
	
	private Integer settleRate;
	private Double settleRateTwo;
	private String materialName;
	private String statusName;
	
	private Integer picCount;
	private Date beginCreateDate;
	private Date endCreateDate;
	
	private String storeId;
	private String storeName;
	private String projectMode;
	private Integer engineDepartId;
	private String engineDepartName;
	
	private	List<String> reimburseStatusList = null;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	private String customerName;
	private String customerPhone;
	
	private Integer itemManagerId;
	private String itemManager;
	private String itemManagerPhone;
	
	
	
	
	public BizMaterialSelfbuyReimburse() {
		super();
	}

	public BizMaterialSelfbuyReimburse(Integer id){
		super(id);
	}

	@Length(min=0, max=10, message="报销类型长度必须介于 0 和 10 之间")
	public String getReimburseType() {
		return reimburseType;
	}

	public void setReimburseType(String reimburseType) {
		this.reimburseType = reimburseType;
	}
	
	public Integer getRelatedReimburseId() {
		return relatedReimburseId;
	}

	public void setRelatedReimburseId(Integer relatedReimburseId) {
		this.relatedReimburseId = relatedReimburseId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getMaterialSelfbuyId() {
		return materialSelfbuyId;
	}

	public void setMaterialSelfbuyId(Integer materialSelfbuyId) {
		this.materialSelfbuyId = materialSelfbuyId;
	}
	
	public Double getCustomerPayAmount() {
		return customerPayAmount;
	}

	public void setCustomerPayAmount(Double customerPayAmount) {
		this.customerPayAmount = customerPayAmount;
	}
	
	public Integer getSettleRate() {
		return settleRate;
	}

	public void setSettleRate(Integer settleRate) {
		this.settleRate = settleRate;
	}
	
	
	public Double getSettleRateTwo() {
		return settleRateTwo;
	}

	public void setSettleRateTwo(Double settleRateTwo) {
		this.settleRateTwo = settleRateTwo;
	}

	@Length(min=0, max=10, message="所属结算阶段长度必须介于 0 和 10 之间")
	public String getSettleStage() {
		return settleStage;
	}

	public void setSettleStage(String settleStage) {
		this.settleStage = settleStage;
	}
	
	public Double getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	
	@Length(min=0, max=255, message="报销说明长度必须介于 0 和 255 之间")
	public String getReimburseRemarks() {
		return reimburseRemarks;
	}

	public void setReimburseRemarks(String reimburseRemarks) {
		this.reimburseRemarks = reimburseRemarks;
	}
	
	@Length(min=0, max=10, message="报销状态长度必须介于 0 和 10 之间")
	public String getReimburseStatus() {
		return reimburseStatus;
	}

	public void setReimburseStatus(String reimburseStatus) {
		this.reimburseStatus = reimburseStatus;
	}
	
	public Date getReimburseStatusDatetime() {
		return reimburseStatusDatetime;
	}

	public void setReimburseStatusDatetime(Date reimburseStatusDatetime) {
		this.reimburseStatusDatetime = reimburseStatusDatetime;
	}
	
	@Length(min=0, max=255, message="报销状态说明长度必须介于 0 和 255 之间")
	public String getReimburseStatusRemarks() {
		return reimburseStatusRemarks;
	}

	public void setReimburseStatusRemarks(String reimburseStatusRemarks) {
		this.reimburseStatusRemarks = reimburseStatusRemarks;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}

	public List<String> getReimburseStatusList() {
		return reimburseStatusList;
	}

	public void setReimburseStatusList(List<String> reimburseStatusList) {
		this.reimburseStatusList = reimburseStatusList;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	
	
}