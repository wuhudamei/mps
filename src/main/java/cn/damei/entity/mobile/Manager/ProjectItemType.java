package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.List;


public class ProjectItemType  implements Serializable{

	

	private static final long serialVersionUID = 1L;
	private Integer  itemTypeId;
	private String itemTypeName;
	private List<ProjectItem> itemList;
	public List<ProjectItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<ProjectItem> itemList) {
		this.itemList = itemList;
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
}
