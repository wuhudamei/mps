/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.mobile.Manager;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 人员签到Entity
 * @author qww
 * @version 2016-11-03
 */
public class BizBusinessSign extends DataEntity2<BizBusinessSign> {
	
	private static final long serialVersionUID = 1L;
	private String signType;		// 签到类型 -- '101.项目经理任务包验收签到;102.项目经理日常签到；201.工人确认开工签到；301.质检约检签到；302.质检抽检签到；303.质检复检签到
	private Integer relatedBusinessIdInt;		// 关联业务id整型 -- '
	private String relatedBusinessIdVarchar;		// 关联业务id字符型 -- '
	private Integer signEmployeeId;		// 签到人员工id -- '
	private Date signDatetime;		// 签到日期时间 -- '
	private String signAddress;		// 签到地址 -- '
	private String signXy;		// 签到坐标 -- '
	private Double signErrorDistance;		// 签到坐标误差 -- '
	private String lat;
	private String lon;
	private String signStep;//签到阶段   10-基装，20-竣工
	private String isValid; //是否合格：0否，1是
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