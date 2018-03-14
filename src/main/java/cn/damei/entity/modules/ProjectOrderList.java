package cn.damei.entity.modules;

import java.util.List;

import cn.damei.common.persistence.DataEntity;

public class ProjectOrderList extends DataEntity<ProjectOrderList> {


	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private List<String> flag;
	private Integer storeId;
	private String projectMode;
	private String orderNumber;
	private String customerName;
	private String customerPhone;
	private String status;
	private String designerName;
	private String designerPhone;
	private String beginCreateDate;
	private String endCreateDate;
	private String engineDepartId;
	private List<String> orderIds;
	private String choiceBillId;
	private String drawingFlag;
	private String attachedFlag;
	private String wallAndFloorFlag;
	private String MaterialFlag;
	private String orderStatusNumber;
	private String isScrap;
	private String engindepartid;
	private String isMaterial;

	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

	public String getChoiceBillId() {
		return choiceBillId;
	}

	public void setChoiceBillId(String choiceBillId) {
		this.choiceBillId = choiceBillId;
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public String getEngindepartid() {
		return engindepartid;
	}

	public void setEngindepartid(String engindepartid) {
		this.engindepartid = engindepartid;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	public List<String> getFlag() {
		return flag;
	}

	public String getIsMaterial() {
		return isMaterial;
	}

	public void setIsMaterial(String isMaterial) {
		this.isMaterial = isMaterial;
	}

	public void setFlag(List<String> flag) {
		this.flag = flag;
	}

	public String getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}

	public String getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(String beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getDrawingFlag() {
		return drawingFlag;
	}

	public void setDrawingFlag(String drawingFlag) {
		this.drawingFlag = drawingFlag;
	}

	public String getAttachedFlag() {
		return attachedFlag;
	}

	public void setAttachedFlag(String attachedFlag) {
		this.attachedFlag = attachedFlag;
	}

	public String getWallAndFloorFlag() {
		return wallAndFloorFlag;
	}

	public void setWallAndFloorFlag(String wallAndFloorFlag) {
		this.wallAndFloorFlag = wallAndFloorFlag;
	}

	public String getMaterialFlag() {
		return MaterialFlag;
	}

	public void setMaterialFlag(String materialFlag) {
		MaterialFlag = materialFlag;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

}
