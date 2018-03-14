package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;

public class DistributeLogEntity implements  Serializable{


	private static final long serialVersionUID = 1L;


	private  Integer orderId;
	private Integer packId;
	private String distributeType;
	private Integer distributeWorkerGroupId;
	private Date createDate;
	private Integer createMan;
	private String delFlag;
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getCreateMan() {
		return createMan;
	}
	public void setCreateMan(Integer createMan) {
		this.createMan = createMan;
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
	public String getDistributeType() {
		return distributeType;
	}
	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}
	public Integer getDistributeWorkerGroupId() {
		return distributeWorkerGroupId;
	}
	public void setDistributeWorkerGroupId(Integer distributeWorkerGroupId) {
		this.distributeWorkerGroupId = distributeWorkerGroupId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	

}
