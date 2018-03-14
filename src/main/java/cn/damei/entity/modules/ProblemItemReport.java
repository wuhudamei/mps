package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class ProblemItemReport extends DataEntity<ProblemItemReport> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer storeId;// 门店ID
	private String projectMode;// 工程模式
	private String acceptArea; // 区域
	private String workType; // 工种
	private String itemName; // 事项名称
	private Double itemCount; // 事项的数量
	private Double workTypeSubtotal; // 小计
	private Integer workTypeSuflag; // 小计标示
	private Double itemProportion; // 事项占比

	private Double acceptAreaPro; // 分区总计
	private Integer accAreaProflag; // 分区总计标示
	private Double workTypePro; // 工种占比
	private Integer workTypeProflag; // 工种占比标示
	private Date itemStartDate; // 事项开始时间
	private Date itemEndDate; // 事项结束时间

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getAcceptArea() {
		return acceptArea;
	}

	public void setAcceptArea(String acceptArea) {
		this.acceptArea = acceptArea;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getItemStartDate() {
		return itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}

	public Date getItemEndDate() {
		return itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		this.itemEndDate = itemEndDate;
	}

	public Double getItemProportion() {
		return itemProportion;
	}

	public void setItemProportion(Double itemProportion) {
		this.itemProportion = itemProportion;
	}

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public Double getWorkTypeSubtotal() {
		return workTypeSubtotal;
	}

	public void setWorkTypeSubtotal(Double workTypeSubtotal) {
		this.workTypeSubtotal = workTypeSubtotal;
	}

	public Double getAcceptAreaPro() {
		return acceptAreaPro;
	}

	public void setAcceptAreaPro(Double acceptAreaPro) {
		this.acceptAreaPro = acceptAreaPro;
	}

	public Double getWorkTypePro() {
		return workTypePro;
	}

	public void setWorkTypePro(Double workTypePro) {
		this.workTypePro = workTypePro;
	}

	public Integer getWorkTypeProflag() {
		return workTypeProflag;
	}

	public void setWorkTypeProflag(Integer workTypeProflag) {
		this.workTypeProflag = workTypeProflag;
	}

	public void setWorkTypeSuflag(Integer workTypeSuflag) {
		this.workTypeSuflag = workTypeSuflag;
	}

	public void setAccAreaProflag(Integer accAreaProflag) {
		this.accAreaProflag = accAreaProflag;
	}

	public Integer getWorkTypeSuflag() {
		return workTypeSuflag;
	}

	public Integer getAccAreaProflag() {
		return accAreaProflag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
