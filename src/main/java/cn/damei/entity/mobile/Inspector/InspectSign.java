package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;


public class InspectSign  implements  Serializable{

	
	

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String signType;
	private Integer relatedBusinessId;
	private Integer signEmployeeId;
	private Date signDateTime;
	private String signAddress;
	private String signXy;
	private Double signErrorDistance;
	private String remarks;
	private Integer createBy;
	private Date createDate;
	private Integer updateBy;
	private Date updateDate;
	private String delFlag;
	private String lat;
	private String lon;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public Integer getSignEmployeeId() {
		return signEmployeeId;
	}
	public void setSignEmployeeId(Integer signEmployeeId) {
		this.signEmployeeId = signEmployeeId;
	}
	public Date getSignDateTime() {
		return signDateTime;
	}
	public void setSignDateTime(Date signDateTime) {
		this.signDateTime = signDateTime;
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
	public Double getSignErrorDistance() {
		return signErrorDistance;
	}
	public void setSignErrorDistance(Double signErrorDistance) {
		this.signErrorDistance = signErrorDistance;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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
	
}
