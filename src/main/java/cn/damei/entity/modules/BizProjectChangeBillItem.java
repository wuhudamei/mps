package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizProjectChangeBillItem extends DataEntity<BizProjectChangeBillItem> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer itemId;
	private Integer projectChangeBillId;
	private String projectIntemTypeName;
	private Integer projectIntemId;
	private String projectIntemMold;
	private String projectIntemCode;
	private String projectIntemName;
	private String projectIntemUnit;
	private String projectType;
	private String groupType;
	private String projectIntemDetail;
	private Double projectIntemAmount;
	private String explainWords;
	
	private Double everyPrice;
	private Double allPrice;
	
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