package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

public class Recheck implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer  reCheckId;//质检单id(复检单)
	private String  reCheckCode;//质检单编号(复检单)
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
	private String  type;//质检单类型 (1:约检单)
	private String isReCheck ;//是否复检  1: 是
	private Integer relatedBillId; //关联质检单id (关联的约检单id)
	private String status;//状态:  1: 创建
	private Integer relatedCheckItemId;//关联质检单检查项id
	private Integer checkItemId;//检查项id
	private String  isOk;//是否合格
	private Double preScores;//原分
	private Double actualScores;//实际得分
	private String changeWay;//整改方式
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
