package cn.damei.entity.mobile.Inspector;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class PqcOrderTaskpackageVo extends DataEntity2<PqcOrderTaskpackageVo>{

	private static final long serialVersionUID = 1L;
	
	private String customerMessage; //客户基本信息
	private String customerName; //客户名称
	private String packageName; // 任务包名称
	private String itemCustomer; // 项目经理名称
	private String phone; // 项目经理电话
	private String houseType; // 户型 1:一居室 2:二居室 3:三居室 4:四居室 5:五居室 6:其它居室
	private String coveredArea; // 建筑面积

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
