package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月24日 上午10:12:44 
* 经理和工人组长的共用签到详情实例 
*/

public class SignDetail extends DataEntity2<SignDetail>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//id
	private Integer orderId;//签到相关订单表id
	private Integer packId;//任务包id
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	private String signPic;//签到照片
	private Date signDate;//签到时间
	private String signAddress;//签到地址
	private String signXy;//签到经纬度
	private String signDistance;//误差距离 (米)
	private String signType;//签到人类型  项目经理还是工人组长
	private String  signName;//签到人名称
	private Integer managerId;//经理id
	private String managerName;//经理名称
	private String managerPhone;
	
	private String lat;
	private String lon;
	
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
	
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	private Integer workerLeaderId;//工人组长id
	private String workerLeaderName;//工人组长名称
	private String customerInfo;//客户信息 (住址加姓名)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getSignPic() {
		return signPic;
	}
	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public String getSignAddress() {
		return signAddress;
	}
	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
	public String getSignXy() {
		return signXy;
	}
	public void setSignXy(String signXy) {
		this.signXy = signXy;
	}
	public String getSignDistance() {
		return signDistance;
	}
	public void setSignDistance(String signDistance) {
		this.signDistance = signDistance;
	}

	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getWorkerLeaderId() {
		return workerLeaderId;
	}
	public void setWorkerLeaderId(Integer workerLeaderId) {
		this.workerLeaderId = workerLeaderId;
	}
	public String getWorkerLeaderName() {
		return workerLeaderName;
	}
	public void setWorkerLeaderName(String workerLeaderName) {
		this.workerLeaderName = workerLeaderName;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
	
}
