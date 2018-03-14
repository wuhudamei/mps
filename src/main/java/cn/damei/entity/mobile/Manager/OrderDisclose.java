package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class OrderDisclose extends DataEntity2<OrderDisclose> {
	private Integer id;

	private Integer orderId;


	private Integer discloseEmployeeId;


	private Date discloseDate;
	

	private String remarks;
	

	private String createByAuthor;
	

	private Date createDate;
	

	private String updateByAuthor;
	

	private Date updateDate;

	private double measureSquare;

	
	public double getMeasureSquare() {
		return measureSquare;
	}

	public void setMeasureSquare(double measureSquare) {
		this.measureSquare = measureSquare;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDiscloseEmployeeId() {
		return discloseEmployeeId;
	}

	public void setDiscloseEmployeeId(Integer discloseEmployeeId) {
		this.discloseEmployeeId = discloseEmployeeId;
	}

	public Date getDiscloseDate() {
		return discloseDate;
	}

	public String getCreateByAuthor() {
		return createByAuthor;
	}

	public void setCreateByAuthor(String createByAuthor) {
		this.createByAuthor = createByAuthor;
	}

	public String getUpdateByAuthor() {
		return updateByAuthor;
	}

	public void setUpdateByAuthor(String updateByAuthor) {
		this.updateByAuthor = updateByAuthor;
	}

	public void setDiscloseDate(Date discloseDate) {
		this.discloseDate = discloseDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
