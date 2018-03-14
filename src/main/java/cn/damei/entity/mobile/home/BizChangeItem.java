package cn.damei.entity.mobile.home;

import java.io.Serializable;

/**
 * 施工变更单  变更项Entity
 * @author wyb
 * @version 2016-11-16
 */
public class BizChangeItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String projectIntemName;//施工项name
	private String projectIntemUnit;//施工项计量单位
	private String projectIntemMold;//增减项
	private Double projectIntemPrice;//单价
	private Integer projectIntemAmount;//数量
	private Double  everyPrice;//总价
	private String explainWords;//说明
	
	private BizProjectChangeBill bizProjectChangeBill; //变更单

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

	public String getProjectIntemMold() {
		return projectIntemMold;
	}

	public void setProjectIntemMold(String projectIntemMold) {
		this.projectIntemMold = projectIntemMold;
	}

	public Double getProjectIntemPrice() {
		return projectIntemPrice;
	}

	public void setProjectIntemPrice(Double projectIntemPrice) {
		this.projectIntemPrice = projectIntemPrice;
	}

	public Integer getProjectIntemAmount() {
		return projectIntemAmount;
	}

	public void setProjectIntemAmount(Integer projectIntemAmount) {
		this.projectIntemAmount = projectIntemAmount;
	}

	public Double getEveryPrice() {
		return everyPrice;
	}

	public void setEveryPrice(Double everyPrice) {
		this.everyPrice = everyPrice;
	}

	public String getExplainWords() {
		return explainWords;
	}

	public void setExplainWords(String explainWords) {
		this.explainWords = explainWords;
	}

	public BizProjectChangeBill getBizProjectChangeBill() {
		return bizProjectChangeBill;
	}

	public void setBizProjectChangeBill(BizProjectChangeBill bizProjectChangeBill) {
		this.bizProjectChangeBill = bizProjectChangeBill;
	}
	
	
	
}