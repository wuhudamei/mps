package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class Sign  extends DataEntity2<Sign>{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String signType;
	private Integer relatedBusinessId;
	private Integer orderId;
	private Integer signEmployeeId;
	private Date signDateTime;
	private String signAddress;
	private String signXy;
	private Double signErrorDistance;
	private String remarks;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
