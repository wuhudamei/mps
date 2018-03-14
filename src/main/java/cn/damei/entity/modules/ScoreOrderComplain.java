package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class ScoreOrderComplain extends DataEntity<ScoreOrderComplain>{
	//门店编号
	private Integer storeId;
	//门店名字
	private String name;
	//客诉类型
	private String label;
	//客诉类型值
	private Integer value;
	//客诉内容
	private String complainContent;
	//客诉 编号
	private String complainCode;
	//客诉时间
	private Date complainTime;
	//订单id
	private String orderId; 
	//订单号
	private String orderNumber;
	//客诉元
	private String employeeName;
	//顾客名字
	private String customerName;
	//顾客电话
	private String customerPhone;
	//订单地址
	private String province; 
	private String city;
	private String county;
	private String detailAddress;
	//起止日期
	private Date dateBegin;
	private Date dateEnd;
	//查询条件
	private String query;
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getComplainContent() {
		return complainContent;
	}
	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}
	public String getComplainCode() {
		return complainCode;
	}
	public void setComplainCode(String complainCode) {
		this.complainCode = complainCode;
	}
	public Date getComplainTime() {
		return complainTime;
	}
	public void setComplainTime(Date complainTime) {
		this.complainTime = complainTime;
	}
	

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@Override
	public String toString() {
		return "ScoreOrderComplain [id=" + id + ", name=" + name + ", label=" + label + ", value=" + value
				+ ", complainContent=" + complainContent + ", complainCode=" + complainCode + ", complainTime="
				+ complainTime + ", orderId=" + orderId + ", orderNumber=" + orderNumber + ", employeeName="
				+ employeeName + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", province="
				+ province + ", city=" + city + ", county=" + county + ", detailAddress=" + detailAddress
				+ ", dateBegin=" + dateBegin + ", dateEnd=" + dateEnd + ", query=" + query + "]";
	}
	
	

}
