package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 梅浩
 * @2016年11月9日
 * @mdn美得你
 * @author_phone : 18610507472
 * @ClassInfo:复检单实体类
 */
public class RecheckEntity  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recheckId;//复检单id
	private String customerName;//客户名称
	private String customerAddress;//客户地址
	private String customerPhone;//客户电话
	private String  managerName;//经理名称
	private String   recheckType;//复检类型 (1:约检 2:抽检)
	private String checkNodeName;//检查节点名称
	private Date hopeCheckTime;//期望验收时间
	private Integer orderId;//订单id	
	private Integer picId;
	private Date updateTime;//合格的更新时间
	private String recheckStatus;//复检单状态
	private String picUrl;//图片路径
	private String checkItemName;//检查项名称
	private Integer checkItemId;//检查项id
	private String isPassed;//是否合格
	private String changeWay;//整改方式
	private Integer recheckTimes;//复检次数
	private Date  recheckDate;//检查日期
	private Integer  recheckManId;//检查人id
	private String lat;
	private String lon;
	private String managerPhone;
	
	
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public Integer getRecheckManId() {
		return recheckManId;
	}
	public void setRecheckManId(Integer recheckManId) {
		this.recheckManId = recheckManId;
	}
	public Date getRecheckDate() {
		return recheckDate;
	}
	public void setRecheckDate(Date recheckDate) {
		this.recheckDate = recheckDate;
	}
	public String getRecheckStatus() {
		return recheckStatus;
	}
	public void setRecheckStatus(String recheckStatus) {
		this.recheckStatus = recheckStatus;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public Integer getRecheckTimes() {
		return recheckTimes;
	}
	public void setRecheckTimes(Integer recheckTimes) {
		this.recheckTimes = recheckTimes;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getCheckItemName() {
		return checkItemName;
	}
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	public String getChangeWay() {
		return changeWay;
	}
	public void setChangeWay(String changeWay) {
		this.changeWay = changeWay;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getRecheckType() {
		return recheckType;
	}
	public void setRecheckType(String recheckType) {
		this.recheckType = recheckType;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getRecheckId() {
		return recheckId;
	}
	public void setRecheckId(Integer recheckId) {
		this.recheckId = recheckId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCheckNodeName() {
		return checkNodeName;
	}
	public void setCheckNodeName(String checkNodeName) {
		this.checkNodeName = checkNodeName;
	}
	public Date getHopeCheckTime() {
		return hopeCheckTime;
	}
	public void setHopeCheckTime(Date hopeCheckTime) {
		this.hopeCheckTime = hopeCheckTime;
	}
	


}
