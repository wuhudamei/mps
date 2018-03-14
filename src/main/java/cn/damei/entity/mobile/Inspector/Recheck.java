package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

public class Recheck implements Serializable {

	
	

	private static final long serialVersionUID = 1L;
	private Integer  reCheckId;
	private String  reCheckCode;
	private Integer orderId;
	private Integer checkNodeId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCheckNodeId() {
		return checkNodeId;
	}
	public void setCheckNodeId(Integer checkNodeId) {
		this.checkNodeId = checkNodeId;
	}
	public Integer getReCheckId() {
		return reCheckId;
	}
	public void setReCheckId(Integer reCheckId) {
		this.reCheckId = reCheckId;
	}
	public String getReCheckCode() {
		return reCheckCode;
	}
	public void setReCheckCode(String reCheckCode) {
		this.reCheckCode = reCheckCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsReCheck() {
		return isReCheck;
	}
	public void setIsReCheck(String isReCheck) {
		this.isReCheck = isReCheck;
	}
	public Integer getRelatedBillId() {
		return relatedBillId;
	}
	public void setRelatedBillId(Integer relatedBillId) {
		this.relatedBillId = relatedBillId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRelatedCheckItemId() {
		return relatedCheckItemId;
	}
	public void setRelatedCheckItemId(Integer relatedCheckItemId) {
		this.relatedCheckItemId = relatedCheckItemId;
	}
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public Double getPreScores() {
		return preScores;
	}
	public void setPreScores(Double preScores) {
		this.preScores = preScores;
	}
	public Double getActualScores() {
		return actualScores;
	}
	public void setActualScores(Double actualScores) {
		this.actualScores = actualScores;
	}
	public String getChangeWay() {
		return changeWay;
	}
	public void setChangeWay(String changeWay) {
		this.changeWay = changeWay;
	}
	private String  type;
	private String isReCheck ;
	private Integer relatedBillId;
	private String status;
	private Integer relatedCheckItemId;
	private Integer checkItemId;
	private String  isOk;
	private Double preScores;
	private Double actualScores;
	private String changeWay;
	private Integer createBy;
	private Integer updateBy;
	private Date createDate;
	private Date updateDate;
	private String  delFlag;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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
	
}
