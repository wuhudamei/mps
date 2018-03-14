package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

public class IllegalModality implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键
	private Integer storeId;// 门店id
	private Integer checkItemId;// 检查项id
	private Double checkItemPunishAmount;// 该检查项处罚金额
	private Integer inspectId;// 质检单id
	private String IllegalModalityName;// 违规形式名称
	private Integer responsibilityPersonW;// 订单模板ID
	private Integer illegalModalityId;// 违规形式id
	private Double managerFineMoney;
	private Double managerFineScore;
	private Double workerFineMoney;
	private Double workerFineScore;
	private Double pqcFineMoney;
	private Double pqcFineScore;

	public Double getManagerFineMoney() {
		return managerFineMoney;
	}

	public void setManagerFineMoney(Double managerFineMoney) {
		this.managerFineMoney = managerFineMoney;
	}

	public Double getManagerFineScore() {
		return managerFineScore;
	}

	public void setManagerFineScore(Double managerFineScore) {
		this.managerFineScore = managerFineScore;
	}

	public Double getWorkerFineMoney() {
		return workerFineMoney;
	}

	public void setWorkerFineMoney(Double workerFineMoney) {
		this.workerFineMoney = workerFineMoney;
	}

	public Double getWorkerFineScore() {
		return workerFineScore;
	}

	public void setWorkerFineScore(Double workerFineScore) {
		this.workerFineScore = workerFineScore;
	}

	public Integer getResponsibilityPersonW() {
		return responsibilityPersonW;
	}

	public void setResponsibilityPersonW(Integer responsibilityPersonW) {
		this.responsibilityPersonW = responsibilityPersonW;
	}

	public Double getPqcFineMoney() {
		return pqcFineMoney;
	}

	public void setPqcFineMoney(Double pqcFineMoney) {
		this.pqcFineMoney = pqcFineMoney;
	}

	public Double getPqcFineScore() {
		return pqcFineScore;
	}

	public void setPqcFineScore(Double pqcFineScore) {
		this.pqcFineScore = pqcFineScore;
	}

	public Integer getIllegalModalityId() {
		return illegalModalityId;
	}

	public void setIllegalModalityId(Integer illegalModalityId) {
		this.illegalModalityId = illegalModalityId;
	}public Integer getInspectId() {
		return inspectId;
	}

	public void setInspectId(Integer inspectId) {
		this.inspectId = inspectId;
	}


	private String isRemarks;// 是否备注
	private String status;// 状态
	private String remarks;// 备注

	@Override
	public String toString() {
		return "IllegalModality [id=" + id + ", storeId=" + storeId + ", checkItemId=" + checkItemId
				+ ", IllegalModalityName=" + IllegalModalityName + ", isRemarks=" + isRemarks + ", status=" + status
				+ ", remakrs=" + remarks + "]";
	}

	public Double getCheckItemPunishAmount() {
		return checkItemPunishAmount;
	}

	public void setCheckItemPunishAmount(Double checkItemPunishAmount) {
		this.checkItemPunishAmount = checkItemPunishAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getCheckItemId() {
		return checkItemId;
	}

	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}

	public String getIllegalModalityName() {
		return IllegalModalityName;
	}

	public void setIllegalModalityName(String illegalModalityName) {
		IllegalModalityName = illegalModalityName;
	}

	public String getIsRemarks() {
		return isRemarks;
	}

	public void setIsRemarks(String isRemarks) {
		this.isRemarks = isRemarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
