package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 梅浩
 * @2016年11月17日
 * @mdn大美装饰管理平台
 * @author_phone : 18610507472
 * @ClassInfo:施工分类
 */
public class ProjectItemType  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer  itemTypeId;//施工分类id
	private String itemTypeName;//施工分类name
	private List<ProjectItem> itemList;//施工项集合
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
