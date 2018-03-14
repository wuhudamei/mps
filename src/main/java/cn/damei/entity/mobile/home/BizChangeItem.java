package cn.damei.entity.mobile.home;

import java.io.Serializable;


public class BizChangeItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String projectIntemName;
	private String projectIntemUnit;
	private String projectIntemMold;
	private Double projectIntemPrice;
	private Integer projectIntemAmount;
	private Double  everyPrice;
	private String explainWords;
	
	private BizProjectChangeBill bizProjectChangeBill;

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