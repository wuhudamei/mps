package cn.damei.entity.modules;

import java.util.List;

import cn.damei.common.persistence.DataEntity;

public class MaterialWarning extends DataEntity<MaterialWarning>{
	private static final long serialVersionUID = 1L;
	private String id;
	private String storeId;
	private String isNewHouse;//新老房 新 1 老 0
	private String constructionScheduleName;//进度节点名称
	private String projectMode;//工程模式  产业 1 传统 2
	private String sort;//进度节点顺序
	private String allOrderSum;
	private List<String> orderIds;
	
	
	public List<String> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}
	public String getAllOrderSum() {
		return allOrderSum;
	}
	public void setAllOrderSum(String allOrderSum) {
		this.allOrderSum = allOrderSum;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getIsNewHouse() {
		return isNewHouse;
	}
	public void setIsNewHouse(String isNewHouse) {
		this.isNewHouse = isNewHouse;
	}
	public String getConstructionScheduleName() {
		return constructionScheduleName;
	}
	public void setConstructionScheduleName(String constructionScheduleName) {
		this.constructionScheduleName = constructionScheduleName;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	
	
}
