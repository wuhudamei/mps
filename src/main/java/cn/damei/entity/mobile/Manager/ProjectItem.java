package cn.damei.entity.mobile.Manager;

import java.io.Serializable;


public class ProjectItem  implements  Serializable{


	private static final long serialVersionUID = 1L;

	
	private Integer itemId;
	private String itemName;
	private ProjectItemType   itemType;
	private String itemWay;
	private String  itemCode;
	private String itemUnit;
	private String groupType;
	private String status;
	private String itemDetail;
	private Double itemPrice;
	private Double  itemTotalPrice;
	private Double itemCount;
	private Integer itemTypeId;
	private String itemTypeName;
	private Integer  changeBillId;
	private String itemDescription;
	private Integer count;
	private String projectIntemMold;
	private String storeId;


	public String getProjectIntemMold() {
		return projectIntemMold;
	}

	public void setProjectIntemMold(String projectIntemMold) {
		this.projectIntemMold = projectIntemMold;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public Integer getChangeBillId() {
		return changeBillId;
	}
	public void setChangeBillId(Integer changeBillId) {
		this.changeBillId = changeBillId;
	}
	public Integer getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Double getItemTotalPrice() {
		return itemTotalPrice;
	}
	public void setItemTotalPrice(Double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}
	public Double getItemCount() {
		return itemCount;
	}
	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemWay() {
		return itemWay;
	}
	public void setItemWay(String itemWay) {
		this.itemWay = itemWay;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}
	public ProjectItemType getItemType() {
		return itemType;
	}
	public void setItemType(ProjectItemType itemType) {
		this.itemType = itemType;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
