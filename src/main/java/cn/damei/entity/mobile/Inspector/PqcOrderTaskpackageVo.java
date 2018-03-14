package cn.damei.entity.mobile.Inspector;

import cn.damei.common.persistence.DataEntity2;



public class PqcOrderTaskpackageVo extends DataEntity2<PqcOrderTaskpackageVo>{

	private static final long serialVersionUID = 1L;
	
	private String customerMessage;
	private String customerName;
	private String packageName;
	private String itemCustomer;
	private String phone;
	private String houseType;
	private String coveredArea;

	public PqcOrderTaskpackageVo() {
		super();
	}

	public PqcOrderTaskpackageVo(Integer id){
		super(id);
	}
	
	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
}
