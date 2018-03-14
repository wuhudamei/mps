package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 变更单 增减项Entity
 * @author wyb
 * @version 2016-11-16
 */
public class BizProjectChangeBillItem extends DataEntity<BizProjectChangeBillItem> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer itemId;		//变更单id
	private Integer projectChangeBillId;	//变更单id
	private String projectIntemTypeName;	//施工项分类名称
	private Integer projectIntemId;		//施工项
	private String projectIntemMold;	//施工项 1增项 2减项
	private String projectIntemCode;	//施工项编码
	private String projectIntemName;	//施工项名称
	private String projectIntemUnit;	//施工项计量单位
	private String projectType;	//施工类型
	private String groupType;	//套餐类型 -- '1.套餐内；2套餐外
	private String projectIntemDetail;	//施工项详情
	private Double projectIntemAmount;	//数量
	private String explainWords;	//说明
	
	private Double everyPrice;	//单价
	private Double allPrice;	//每一项的总价 =数量*单价
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getProjectChangeBillId() {
		return projectChangeBillId;
	}
	public void setProjectChangeBillId(Integer projectChangeBillId) {
		this.projectChangeBillId = projectChangeBillId;
	}
	public String getProjectIntemTypeName() {
		return projectIntemTypeName;
	}
	public void setProjectIntemTypeName(String projectIntemTypeName) {
		this.projectIntemTypeName = projectIntemTypeName;
	}
	public String getProjectIntemMold() {
		return projectIntemMold;
	}
	public void setProjectIntemMold(String projectIntemMold) {
		this.projectIntemMold = projectIntemMold;
	}
	public String getProjectIntemCode() {
		return projectIntemCode;
	}
	public void setProjectIntemCode(String projectIntemCode) {
		this.projectIntemCode = projectIntemCode;
	}
	public String getProjectIntemName() {
		return projectIntemName;
	}
	public void setProjectIntemName(String projectIntemName) {
		this.projectIntemName = projectIntemName;
	}
	public String getProjectIntemUnit() {
		return projectIntemUnit;
	}
	public void setProjectIntemUnit(String projectIntemUnit) {
		this.projectIntemUnit = projectIntemUnit;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getProjectIntemDetail() {
		return projectIntemDetail;
	}
	public void setProjectIntemDetail(String projectIntemDetail) {
		this.projectIntemDetail = projectIntemDetail;
	}
	public Double getProjectIntemAmount() {
		return projectIntemAmount;
	}
	public void setProjectIntemAmount(Double projectIntemAmount) {
		this.projectIntemAmount = projectIntemAmount;
	}
	public String getExplainWords() {
		return explainWords;
	}
	public void setExplainWords(String explainWords) {
		this.explainWords = explainWords;
	}
	public Double getEveryPrice() {
		return everyPrice;
	}
	public void setEveryPrice(Double everyPrice) {
		this.everyPrice = everyPrice;
	}
	public Double getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}
	public Integer getProjectIntemId() {
		return projectIntemId;
	}
	public void setProjectIntemId(Integer projectIntemId) {
		this.projectIntemId = projectIntemId;
	}
	
	
}