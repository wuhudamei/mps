package cn.damei.entity.mobile.Manager;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	private Integer packId; // 任务包id
	private String signPic;//签到照片
	private Date signDate;//签到时间
	private String signAddress;//签到地址
	private String signXy;//签到经纬度
	private Double signDistance;//误差距离 (米)
	private String signType;//签到人类型  项目经理还是工人组长
	private String  signName;//签到人名称
	private Integer managerId;//经理id
	private String managerName;//经理名称
	private Integer workerLeaderId;//工人组长id
	private String workerLeaderName;//工人组长名称
	private Integer inspectorId;//质检员id
	private Integer inspectId;//质检单id
	private String customerAddress;
	private String lat;
	private String lon;
	private String signStep;//签到阶段   10-基装，20-竣工
	private String isValid; //是否合格：0否，1是
	private String[] photo;//图片
	private String storeId;
	private String storeName;
	//工程模式
	private String projectMode;
	private String projectModeName;
	//客户姓名
	private String customerName;
	
	private String enginDepartName;
	//区域ID
	private String enginDepartId;
	//延期天数
	private String delayDays;
	//订单编号
	private String orderNumber;
	//交底时间
	private Date demolitionBuildDate;
	private Date startDemolitionBuildDate;
	private Date endDemolitionBuildDate;
	private Integer signId;

	public Integer getSignId() {
		return signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getDemolitionBuildDate() {
		return demolitionBuildDate;
	}

	public void setDemolitionBuildDate(Date demolitionBuildDate) {
		this.demolitionBuildDate = demolitionBuildDate;
	}

	public Date getStartDemolitionBuildDate() {
		return startDemolitionBuildDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setStartDemolitionBuildDate(Date startDemolitionBuildDate) {
		this.startDemolitionBuildDate = startDemolitionBuildDate;
	}

	public Date getEndDemolitionBuildDate() {
		return endDemolitionBuildDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setEndDemolitionBuildDate(Date endDemolitionBuildDate) {
		this.endDemolitionBuildDate = endDemolitionBuildDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public String getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String[] getPhoto() {
		return photo;
	}

	public void setPhoto(String[] photo) {
		this.photo = photo;
	}

	public String getSignStep() {
		return signStep;
	}

	public void setSignStep(String signStep) {
		this.signStep = signStep;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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
	public Integer getInspectId() {
		return inspectId;
	}
	public void setInspectId(Integer inspectId) {
		this.inspectId = inspectId;
	}
	public Integer getInspectorId() {
		return inspectorId;
	}
	public void setInspectorId(Integer inspectorId) {
		this.inspectorId = inspectorId;
	}
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	private String inspectorName;//质检员名称
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
	
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
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
	
	public Double getSignDistance() {
		return signDistance;
	}
	public void setSignDistance(Double signDistance) {
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
