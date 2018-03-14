package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import cn.damei.common.persistence.DataEntity;



public class WorkgroupVo extends DataEntity<WorkgroupVo> {


	private static final long serialVersionUID = 1L;

	private String groupId;
	private String groupName;
	private String phone;
	private Integer workerGroupId;
	private Integer starLevel;
	private String NPS;
	private Integer groupCount;
	private String itemManagerId;
	private String itemManageName;
	private String itemManagerPhone;
	public String getHeadPic() {
		return headPic;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		WorkgroupVo that = (WorkgroupVo) o;
		return Objects.equals(groupId, that.groupId) &&
				Objects.equals(groupName, that.groupName) &&
				Objects.equals(phone, that.phone) &&
				Objects.equals(workerGroupId, that.workerGroupId) &&
				Objects.equals(starLevel, that.starLevel) &&
				Objects.equals(NPS, that.NPS) &&
				Objects.equals(groupCount, that.groupCount) &&
				Objects.equals(headPic, that.headPic) &&
				Objects.equals(elactricationId, that.elactricationId) &&
				Objects.equals(elactricationName, that.elactricationName) &&
				Objects.equals(targetPackageCount, that.targetPackageCount) &&
				Objects.equals(address, that.address) &&
				Objects.equals(addressToWorkerAddressDistance, that.addressToWorkerAddressDistance) &&
				Objects.equals(packageStartTime, that.packageStartTime) &&
				Objects.equals(targetPackageId, that.targetPackageId) &&
				Objects.equals(lng, that.lng) &&
				Objects.equals(lat, that.lat) &&
				Objects.equals(packageId, that.packageId) &&
				Objects.equals(packLng, that.packLng) &&
				Objects.equals(packLat, that.packLat) &&
				Objects.equals(packTempId, that.packTempId) &&
				Objects.equals(list, that.list) &&
				Objects.equals(scopeDistance, that.scopeDistance) &&
				Objects.equals(planStartDate, that.planStartDate) &&
				Objects.equals(planEndDate, that.planEndDate) &&
				Objects.equals(isSpecialPack, that.isSpecialPack);
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupId, groupName, phone, workerGroupId, starLevel, NPS, groupCount, headPic, elactricationId, elactricationName, targetPackageCount, address, addressToWorkerAddressDistance, packageStartTime, targetPackageId, lng, lat, packageId, packLng, packLat, packTempId, list, scopeDistance, planStartDate, planEndDate, isSpecialPack);
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	private String headPic;
	public Integer getWorkerGroupId() {
		return workerGroupId;
	}

	public void setWorkerGroupId(Integer workerGroupId) {
		this.workerGroupId = workerGroupId;
	}

	private String elactricationId;
	private String elactricationName;
	private Integer targetPackageCount;
	private String address;
	private String addressToWorkerAddressDistance;
	private Date packageStartTime;
	private String targetPackageId;
	private String packageCode;
	private Double lng;
	private Double lat;
	
	private Integer packageId;

	private Double packLng;
	private Double packLat;

	private Integer exchangeOrderTimes;
	
	public Integer getExchangeOrderTimes() {
		return exchangeOrderTimes;
	}

	public void setExchangeOrderTimes(Integer exchangeOrderTimes) {
		this.exchangeOrderTimes = exchangeOrderTimes;
	}

	public Integer getPackTempId() {
		return packTempId;
	}

	public void setPackTempId(Integer packTempId) {
		this.packTempId = packTempId;
	}

	private Integer packTempId;
	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	private List<Integer> list;
	public Double getPackLng() {
		return packLng;
	}

	public void setPackLng(Double packLng) {
		this.packLng = packLng;
	}

	public Double getPackLat() {
		return packLat;
	}

	public void setPackLat(Double packLat) {
		this.packLat = packLat;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAddressToWorkerAddressDistance() {
		return addressToWorkerAddressDistance;
	}

	public void setAddressToWorkerAddressDistance(String addressToWorkerAddressDistance) {
		this.addressToWorkerAddressDistance = addressToWorkerAddressDistance;
	}

	public String getTargetPackageId() {
		return targetPackageId;
	}

	public void setTargetPackageId(String targetPackageId) {
		this.targetPackageId = targetPackageId;
	}

	public String getGroupId() {
		return groupId;
	}

	public Date getPackageStartTime() {
		return packageStartTime;
	}

	public void setPackageStartTime(Date packageStartTime) {
		this.packageStartTime = packageStartTime;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	public String getNPS() {
		return NPS;
	}

	public void setNPS(String nPS) {
		NPS = nPS;
	}

	public Integer getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}

	public String getElactricationId() {
		return elactricationId;
	}

	public void setElactricationId(String elactricationId) {
		this.elactricationId = elactricationId;
	}

	public String getElactricationName() {
		return elactricationName;
	}

	public void setElactricationName(String elactricationName) {
		this.elactricationName = elactricationName;
	}

	public Integer getTargetPackageCount() {
		return targetPackageCount;
	}

	public void setTargetPackageCount(Integer targetPackageCount) {
		this.targetPackageCount = targetPackageCount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	private Double   scopeDistance;
	private Date planStartDate;
	private Date planEndDate;
	public Double getScopeDistance() {
		return scopeDistance;
	}

	public void setScopeDistance(Double scopeDistance) {
		this.scopeDistance = scopeDistance;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}


	public String getIsSpecialPack() {
		return isSpecialPack;
	}

	public void setIsSpecialPack(String isSpecialPack) {
		this.isSpecialPack = isSpecialPack;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	private String isSpecialPack;
	public String getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManageName() {
		return itemManageName;
	}

	public void setItemManageName(String itemManageName) {
		this.itemManageName = itemManageName;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	

}
