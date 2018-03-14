package cn.damei.entity.modules;

import java.util.Date;


import cn.damei.common.persistence.DataEntity2;


public class PurchaseStatistics extends DataEntity2<PurchaseStatistics> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String storeName;
	
	private String projectMode;
	private String projectModeName;
	
	private Date beginDateTime;
	private Date endDateTime;
	
	private String type;
	private Integer typeCount;
	
	private Integer auxiliaryApplyCount;
	private Integer auxiliaryTransferSupplierCount;
	private Integer auxiliaryReceiveCount;
	
	private Integer sandApplyCount;
	private Integer sandTransferSupplierCount;
	private Integer sandReceiveCount;
	
	private Integer standardApplyCount;
	private Integer standardReceiveCount;

	private Integer downlightApplyCount;
	private Integer downlightReceiveCount;
	
	private Integer wallFloorApplyCount;
	private Integer wallFloorTransferSupplierCount;
	private Integer wallFloorReceiveCount;
	
	private Integer mainPanelApplyCount;
	private Integer mainPanelTransferSupplierCount;
	private Integer mainPanelReceiveCount;
	
	
	public PurchaseStatistics() {
		super();
	}

	public PurchaseStatistics(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
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

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public Date getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(Date beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Integer getAuxiliaryApplyCount() {
		return auxiliaryApplyCount;
	}

	public void setAuxiliaryApplyCount(Integer auxiliaryApplyCount) {
		this.auxiliaryApplyCount = auxiliaryApplyCount;
	}

	public Integer getAuxiliaryTransferSupplierCount() {
		return auxiliaryTransferSupplierCount;
	}

	public void setAuxiliaryTransferSupplierCount(Integer auxiliaryTransferSupplierCount) {
		this.auxiliaryTransferSupplierCount = auxiliaryTransferSupplierCount;
	}

	public Integer getAuxiliaryReceiveCount() {
		return auxiliaryReceiveCount;
	}

	public void setAuxiliaryReceiveCount(Integer auxiliaryReceiveCount) {
		this.auxiliaryReceiveCount = auxiliaryReceiveCount;
	}

	public Integer getSandApplyCount() {
		return sandApplyCount;
	}

	public void setSandApplyCount(Integer sandApplyCount) {
		this.sandApplyCount = sandApplyCount;
	}

	public Integer getSandTransferSupplierCount() {
		return sandTransferSupplierCount;
	}

	public void setSandTransferSupplierCount(Integer sandTransferSupplierCount) {
		this.sandTransferSupplierCount = sandTransferSupplierCount;
	}

	public Integer getSandReceiveCount() {
		return sandReceiveCount;
	}

	public void setSandReceiveCount(Integer sandReceiveCount) {
		this.sandReceiveCount = sandReceiveCount;
	}

	public Integer getStandardApplyCount() {
		return standardApplyCount;
	}

	public void setStandardApplyCount(Integer standardApplyCount) {
		this.standardApplyCount = standardApplyCount;
	}

	public Integer getStandardReceiveCount() {
		return standardReceiveCount;
	}

	public void setStandardReceiveCount(Integer standardReceiveCount) {
		this.standardReceiveCount = standardReceiveCount;
	}

	public Integer getDownlightApplyCount() {
		return downlightApplyCount;
	}

	public void setDownlightApplyCount(Integer downlightApplyCount) {
		this.downlightApplyCount = downlightApplyCount;
	}

	public Integer getDownlightReceiveCount() {
		return downlightReceiveCount;
	}

	public void setDownlightReceiveCount(Integer downlightReceiveCount) {
		this.downlightReceiveCount = downlightReceiveCount;
	}

	public Integer getWallFloorApplyCount() {
		return wallFloorApplyCount;
	}

	public void setWallFloorApplyCount(Integer wallFloorApplyCount) {
		this.wallFloorApplyCount = wallFloorApplyCount;
	}

	public Integer getWallFloorTransferSupplierCount() {
		return wallFloorTransferSupplierCount;
	}

	public void setWallFloorTransferSupplierCount(Integer wallFloorTransferSupplierCount) {
		this.wallFloorTransferSupplierCount = wallFloorTransferSupplierCount;
	}

	public Integer getWallFloorReceiveCount() {
		return wallFloorReceiveCount;
	}

	public void setWallFloorReceiveCount(Integer wallFloorReceiveCount) {
		this.wallFloorReceiveCount = wallFloorReceiveCount;
	}

	public Integer getMainPanelApplyCount() {
		return mainPanelApplyCount;
	}

	public void setMainPanelApplyCount(Integer mainPanelApplyCount) {
		this.mainPanelApplyCount = mainPanelApplyCount;
	}

	public Integer getMainPanelTransferSupplierCount() {
		return mainPanelTransferSupplierCount;
	}

	public void setMainPanelTransferSupplierCount(Integer mainPanelTransferSupplierCount) {
		this.mainPanelTransferSupplierCount = mainPanelTransferSupplierCount;
	}

	public Integer getMainPanelReceiveCount() {
		return mainPanelReceiveCount;
	}

	public void setMainPanelReceiveCount(Integer mainPanelReceiveCount) {
		this.mainPanelReceiveCount = mainPanelReceiveCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}
	
	
	

}