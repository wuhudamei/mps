package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class OrderSignVo extends DataEntity2<OrderSignVo>{


	private static final long serialVersionUID = 1L;

	


	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	private String customerAddress;
		private Integer  itemManagerId;
		private String  customerName;
		private String communityName;
		private String buildNumber;
		private String buildUnit;
		private String buildRoom;
		private  Date contractStartDate;
		private Date contractEndDate;
		private Date actualStartDate;
		private Integer contractTime;
		private String orderStatus;
		private String mapAddress;
		private String signFlag;
		private Integer count;
		private Date signDate;
		private String lat;
		private String lon;
		private Integer inspectorId;
		private Date jzCheckTime;
		private Date jgCheckTime;
		private String jzShouldSignDays;
		private String jgShouldSignDays;
		private String jzShouldSignTimes;
		private String jgShouldSignTimes;
		private Integer jzAlreadySignTimes;
		private Integer jgAlreadySignTimes;
		private String storeId;
		
		
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
		}
		
		
	
	
	
	
}
