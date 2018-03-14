package cn.damei.Quartz;

import java.io.Serializable;

public class OrderJsonMapping  implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private Integer  businessType;
	private  String mappingField;
	private  String dataFrom;
	private String dataTo;
	private Integer dataId;
	private String dataJson;
	
	
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public String getDataJson() {
		return dataJson;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public String getMappingField() {
		return mappingField;
	}
	public void setMappingField(String mappingField) {
		this.mappingField = mappingField;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getDataTo() {
		return dataTo;
	}
	public void setDataTo(String dataTo) {
		this.dataTo = dataTo;
	}
}
