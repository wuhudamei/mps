package cn.damei.entity.mobile.Manager;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 供应商管理Entity
 */
public class Supplier extends DataEntity2<Supplier> {
	
	private static final long serialVersionUID = 1L;
	private Integer supplierId;	//供应商id
	private String supplierNo;		// 供应商编号
	private String supplierName;		// 供应商名称
	private String contacts;		// 联系人
	private String contactsPhone;		// 联系电话
	private String email;		// 邮箱
	private String status;		// 状态
	private String isSandCement;//辅料商品 是否是沙子水泥
	private Integer orderId;	//订单ID
	private String isElevator;	//订单 是否有电梯
	private Integer floor;	//订单 楼层
	
	private List<SandGoods> sandGoodsList;//商品(沙子水泥)
	
	public Supplier() {
		super();
	}

	public Supplier(Integer id){
		super(id);
	}

	@Length(min=1, max=18, message="供应商编号长度必须介于 1 和 18 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Length(min=1, max=255, message="供应商名称长度必须介于 1 和 255 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=100, message="联系人长度必须介于 0 和 100 之间")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getIsSandCement() {
		return isSandCement;
	}

	public void setIsSandCement(String isSandCement) {
		this.isSandCement = isSandCement;
	}

	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<SandGoods> getSandGoodsList() {
		return sandGoodsList;
	}

	public void setSandGoodsList(List<SandGoods> sandGoodsList) {
		this.sandGoodsList = sandGoodsList;
	}

	
	
	
}