package cn.damei.entity.mobile.Inspector;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class MaterialsInfo{

	private  String assistItemName;
	
	private  String assistItemNo;
	 
	private String unit;
	
	private Double unitPrice;
	
	private Double workerPrice;
	
	private Double supplierPrice;
	
	private String supplierCode;
	
	private String supplierName;
	
	private String lastCount;
	
	private String materialCateName;
	
	private String brand;
	
	private String type;
	
	private Date acceptanceDate;
	
	private String projectManagerName;
	
	private String projectManagerMobile;
	

	public String getAcceptanceDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(acceptanceDate != null){
			return sdf.format(acceptanceDate);
		}else{
			return null;
		}
		
	}

	
	public String getAssistItemNo() {
		return assistItemNo;
	}


	public void setAssistItemNo(String assistItemNo) {
		this.assistItemNo = assistItemNo;
	}


	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	public String getProjectManagerMobile() {
		return projectManagerMobile;
	}

	public void setProjectManagerMobile(String projectManagerMobile) {
		this.projectManagerMobile = projectManagerMobile;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssistItemName() {
		return assistItemName;
	}

	public void setAssistItemName(String assistItemName) {
		this.assistItemName = assistItemName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getUnitPrice() {
		if(unitPrice == null){
			unitPrice=0.00;
		}
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getLastCount() {
		return lastCount;
	}

	public void setLastCount(String lastCount) {
		this.lastCount = lastCount;
	}

	public String getMaterialCateName() {
		return materialCateName;
	}

	public void setMaterialCateName(String materialCateName) {
		this.materialCateName = materialCateName;
	}

	public Double getWorkerPrice() {
		return workerPrice;
	}

	public void setWorkerPrice(Double workerPrice) {
		this.workerPrice = workerPrice;
	}

	public Double getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	
	
}
