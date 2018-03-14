package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月22日 下午6:35:08 
* 项目经理(ItemManager)     订单(Order)   质检员(Inspector)  签到(Sign)      Vo
*/

public class OrderSignVo extends DataEntity2<OrderSignVo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//订单id主键在 爷爷类中

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	private String customerAddress;
		private Integer  itemManagerId; //关键的项目经理id
		private String  customerName;//客户姓名
		private String communityName;//小区名称
		private String buildNumber;//楼号
		private String buildUnit;//单元号
		private String buildRoom;//门牌号   哪一室
		private  Date contractStartDate;//合同开工日期
		private Date contractEndDate;//合同结束日期
		private Date actualStartDate;//实际开工日期
		private Integer contractTime;//合同工期
		private String orderStatus;//订单状态
		private String mapAddress;//用户坐标
		private String signFlag;//是否签到
		private Integer count;//签到数量
		private Date signDate;
		private String lat;
		private String lon;
		private Integer inspectorId;
		private Date jzCheckTime; 			//基装验收时间
		private Date jgCheckTime; 			//竣工验收时间
		private String jzShouldSignDays;   //基装应签到天数
		private String jgShouldSignDays;   //竣工应签到天数
		private String jzShouldSignTimes;  //基装应签到次数
		private String jgShouldSignTimes;  //竣工应签到次数
		private Integer jzAlreadySignTimes; //基装已签到次数
		private Integer jgAlreadySignTimes; //竣工已签到次数
		private String storeId; //门店
		
		
		public Date getJzCheckTime() {
			return jzCheckTime;
		}

		public void setJzCheckTime(Date jzCheckTime) {
			this.jzCheckTime = jzCheckTime;
		}

		public Date getJgCheckTime() {
			return jgCheckTime;
		}

		public void setJgCheckTime(Date jgCheckTime) {
			this.jgCheckTime = jgCheckTime;
		}

		public String getJzShouldSignDays() {
			return jzShouldSignDays;
		}

		public void setJzShouldSignDays(String jzShouldSignDays) {
			this.jzShouldSignDays = jzShouldSignDays;
		}

		public String getJgShouldSignDays() {
			return jgShouldSignDays;
		}

		public void setJgShouldSignDays(String jgShouldSignDays) {
			this.jgShouldSignDays = jgShouldSignDays;
		}

		public String getJzShouldSignTimes() {
			return jzShouldSignTimes;
		}

		public void setJzShouldSignTimes(String jzShouldSignTimes) {
			this.jzShouldSignTimes = jzShouldSignTimes;
		}

		public String getJgShouldSignTimes() {
			return jgShouldSignTimes;
		}

		public void setJgShouldSignTimes(String jgShouldSignTimes) {
			this.jgShouldSignTimes = jgShouldSignTimes;
		}

		public Integer getJzAlreadySignTimes() {
			return jzAlreadySignTimes;
		}

		public void setJzAlreadySignTimes(Integer jzAlreadySignTimes) {
			this.jzAlreadySignTimes = jzAlreadySignTimes;
		}

		public Integer getJgAlreadySignTimes() {
			return jgAlreadySignTimes;
		}

		public void setJgAlreadySignTimes(Integer jgAlreadySignTimes) {
			this.jgAlreadySignTimes = jgAlreadySignTimes;
		}

		public String getStoreId() {
			return storeId;
		}

		public void setStoreId(String storeId) {
			this.storeId = storeId;
		}

		public Integer getInspectorId() {
			return inspectorId;
		}
		public void setInspectorId(Integer inspectorId) {
			this.inspectorId = inspectorId;
		}
		public Date getSignDate() {
			return signDate;
		}
		public void setSignDate(Date signDate) {
			this.signDate = signDate;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
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
		public String getSignFlag() {
			return signFlag;
		}
		public void setSignFlag(String signFlag) {
			this.signFlag = signFlag;
		}
		public String getMapAddress() {
			return mapAddress;
		}
		public void setMapAddress(String mapAddress) {
			this.mapAddress = mapAddress;
		}
		public String getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
		public Date getContractEndDate() {
			return contractEndDate;
		}
		public void setContractEndDate(Date contractEndDate) {
			this.contractEndDate = contractEndDate;
		}
		public Integer getContractTime() {
			return contractTime;
		}
		public void setContractTime(Integer contractTime) {
			
			this.contractTime = contractTime;
		}
		public OrderSignVo() {
			super();
		}
		public OrderSignVo(Integer id) {
			super(id);
		}
		public Integer getItemManagerId() {
			return itemManagerId;
		}
		public void setItemManagerId(Integer itemManagerId) {
			this.itemManagerId = itemManagerId;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getCommunityName() {
			return communityName;
		}
		public void setCommunityName(String communityName) {
			this.communityName = communityName;
		}
	
		public String getBuildNumber() {
			return buildNumber;
		}
		public void setBuildNumber(String buildNumber) {
			this.buildNumber = buildNumber;
		}
		public String getBuildUnit() {
			return buildUnit;
		}
		public void setBuildUnit(String buildUnit) {
			this.buildUnit = buildUnit;
		}
		public String getBuildRoom() {
			return buildRoom;
		}
		public void setBuildRoom(String buildRoom) {
			this.buildRoom = buildRoom;
		}
		public Date getContractStartDate() {
			return contractStartDate;
		}
		public void setContractStartDate(Date contractStartDate) {
			this.contractStartDate = contractStartDate;
		}
		public Date getActualStartDate() {
			return actualStartDate;
		}
		public void setActualStartDate(Date actualStartDate) {
			this.actualStartDate = actualStartDate;
		} //实际开工日期
		
		
	
	
	
	
}
