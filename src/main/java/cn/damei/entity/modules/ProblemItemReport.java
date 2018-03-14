package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class ProblemItemReport extends DataEntity<ProblemItemReport> {


	private static final long serialVersionUID = 1L;

	private Integer storeId;
	private String projectMode;
	private String acceptArea;
	private String workType;
	private String itemName;
	private Double itemCount;
	private Double workTypeSubtotal;
	private Integer workTypeSuflag;
	private Double itemProportion;

	private Double acceptAreaPro;
	private Integer accAreaProflag;
	private Double workTypePro;
	private Integer workTypeProflag;
	private Date itemStartDate;
	private Date itemEndDate;

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
