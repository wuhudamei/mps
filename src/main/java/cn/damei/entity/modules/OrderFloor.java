package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单地板地砖面积实体类
 * 
 * @author hyh
 *
 */
public class OrderFloor {

	private static final long serialVersionUID = 1L;

	private Integer orderId;// 订单Id

	private String orderNumber;// 订单编号

	private Double floorSettleArea;// 木地板结算面积

	private Double floorTileBudgetArea;// 地砖预算面积

	private Double floorTileSettleArea;// 地砖结算面积

	private Date payDate; //泥瓦工程任务包首款付款完成时间

	private Integer storeId;//门店
	
	private String projectMode;//工程模式

	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	private Integer enginDepartId;//区域
	
	private String enginDepartName;
	
	private String customerName; // 客户姓名
	
	private String communityName; // 小区名称
	
	private String buildNumber; // 几号楼
	
	private String buildUnit; // 几单元
	
	private String buildRoom; // 哪一室
	
	private String customerAddress; // 客户地址
	
	private String itemManager;//项目经理
	
	private Date startDate;
	
	private Date endDate;


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getFloorSettleArea() {
		return floorSettleArea;
	}

	public void setFloorSettleArea(Double floorSettleArea) {
		this.floorSettleArea = floorSettleArea;
	}

	public Double getFloorTileBudgetArea() {
		return floorTileBudgetArea;
	}

	public void setFloorTileBudgetArea(Double floorTileBudgetArea) {
		this.floorTileBudgetArea = floorTileBudgetArea;
	}

	public Double getFloorTileSettleArea() {
		return floorTileSettleArea;
	}

	public void setFloorTileSettleArea(Double floorTileSettleArea) {
		this.floorTileSettleArea = floorTileSettleArea;
	}

}
