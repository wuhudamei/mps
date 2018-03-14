package cn.damei.entity.mobile.Inspector;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class MaterialsInfo{

	private  String assistItemName; //辅材名称
	
	private  String assistItemNo;//辅材编号
	 
	private String unit;  //计量单位
	
	private Double unitPrice; //单价（门店结算价）
	
	private Double workerPrice; //工人结算价
	
	private Double supplierPrice; //供应商供货价
	
	private String supplierCode; //供应商Code
	
	private String supplierName; //供应商名称
	
	private String lastCount; //最终数量
	
	private String materialCateName; //材料分类名称
	
	private String brand;//品牌
	
	private String type;//型号
	
	private Date acceptanceDate;//验收日期
	
	private String projectManagerName;//验收人
	
	private String projectManagerMobile;//验收人手机号
	

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
