package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检员签到
 * @author 93173
 *
 */
public class Sign  extends DataEntity2<Sign>{

	
	private static final long serialVersionUID = 1L;
	private Integer id; //签到id
	private String signType;//签到类型      质检员抽检签到 302
	private Integer relatedBusinessId;//关联业务表id   质检单id
	private Integer orderId;//订单id  
	private Integer signEmployeeId;//签到人id   质检员id
	private Date signDateTime;//签到时间
	private String signAddress;//签到地址
	private String signXy;//签到坐标
	private Double signErrorDistance;//签到误差
	private String remarks;//备注
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
