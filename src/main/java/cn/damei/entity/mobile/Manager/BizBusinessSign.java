
package cn.damei.entity.mobile.Manager;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizBusinessSign extends DataEntity2<BizBusinessSign> {
	
	private static final long serialVersionUID = 1L;
	private String signType;
	private Integer relatedBusinessIdInt;
	private String relatedBusinessIdVarchar;
	private Integer signEmployeeId;
	private Date signDatetime;
	private String signAddress;
	private String signXy;
	private Double signErrorDistance;
	private String lat;
	private String lon;
	private String signStep;
	private String isValid;
	private String orderId;
	
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public BizBusinessSign() {
		super();
	}

	public BizBusinessSign(Integer id){
		super(id);
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	public Integer getRelatedBusinessIdInt() {
		return relatedBusinessIdInt;
	}

	public void setRelatedBusinessIdInt(Integer relatedBusinessIdInt) {
		this.relatedBusinessIdInt = relatedBusinessIdInt;
	}
	
	@Length(min=0, max=64, message="关联业务id字符型 -- '长度必须介于 0 和 64 之间")
	public String getRelatedBusinessIdVarchar() {
		return relatedBusinessIdVarchar;
	}

	public void setRelatedBusinessIdVarchar(String relatedBusinessIdVarchar) {
		this.relatedBusinessIdVarchar = relatedBusinessIdVarchar;
	}
	
	public Integer getSignEmployeeId() {
		return signEmployeeId;
	}

	public void setSignEmployeeId(Integer signEmployeeId) {
		this.signEmployeeId = signEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignDatetime() {
		return signDatetime;
	}

	public void setSignDatetime(Date signDatetime) {
		this.signDatetime = signDatetime;
	}
	
	@Length(min=0, max=255, message="签到地址 -- '长度必须介于 0 和 255 之间")
	public String getSignAddress() {
		return signAddress;
	}

	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
	
	@Length(min=0, max=100, message="签到坐标 -- '长度必须介于 0 和 100 之间")
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