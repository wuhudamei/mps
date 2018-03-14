package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class ApplyMaterialSelfbuyReimburseStatusLog extends DataEntity2<ApplyMaterialSelfbuyReimburseStatusLog> {

	private static final long serialVersionUID = 1L;
	
	private String businessType;
	private Integer businessOnlyMarkInt;
	private String businessOnlyMarkVarchar;
	private String businessStatus;
	private Date statusDatetime;
	private String businessRemarks;
	private Integer businessEmployeeId;
	private String businessEmployeeName;
	private String businessEmployeePhone;
	private String statusName;
	private Integer relatedReimburseId;
	private Integer orderId;
	private String createById;
	

	public String getCreateById() {
		return createById;
	}

	public void setCreateById(String createById) {
		this.createById = createById;
	}

	public ApplyMaterialSelfbuyReimburseStatusLog() {
		super();
	}

	public ApplyMaterialSelfbuyReimburseStatusLog(Integer id){
		super(id);
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}

	public String getBusinessOnlyMarkVarchar() {
		return businessOnlyMarkVarchar;
	}

	public void setBusinessOnlyMarkVarchar(String businessOnlyMarkVarchar) {
		this.businessOnlyMarkVarchar = businessOnlyMarkVarchar;
	}

	public String getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public String getBusinessRemarks() {
		return businessRemarks;
	}

	public void setBusinessRemarks(String businessRemarks) {
		this.businessRemarks = businessRemarks;
	}

	public Integer getBusinessEmployeeId() {
		return businessEmployeeId;
	}

	public void setBusinessEmployeeId(Integer businessEmployeeId) {
		this.businessEmployeeId = businessEmployeeId;
	}

	public String getBusinessEmployeeName() {
		return businessEmployeeName;
	}

	public void setBusinessEmployeeName(String businessEmployeeName) {
		this.businessEmployeeName = businessEmployeeName;
	}


	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getRelatedReimburseId() {
		return relatedReimburseId;
	}

	public void setRelatedReimburseId(Integer relatedReimburseId) {
		this.relatedReimburseId = relatedReimburseId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getBusinessEmployeePhone() {
		return businessEmployeePhone;
	}

	public void setBusinessEmployeePhone(String businessEmployeePhone) {
		this.businessEmployeePhone = businessEmployeePhone;
	}
	
	





}
