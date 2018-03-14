package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class ApplyMaterialSelfbuyReimburseStatusLog extends DataEntity2<ApplyMaterialSelfbuyReimburseStatusLog> {

	private static final long serialVersionUID = 1L;
	
	private String businessType;		// 业务类型
	private Integer businessOnlyMarkInt;		// 业务唯一标识整形
	private String businessOnlyMarkVarchar;		// 业务唯一标识字符型
	private String businessStatus;		// 业务状态
	private Date statusDatetime;		// 状态时间
	private String businessRemarks;		// 业务备注
	private Integer businessEmployeeId;		// 业务人员员工id
	private String businessEmployeeName;
	private String businessEmployeePhone;
	private String statusName;	//状态名称
	private Integer relatedReimburseId;		// 关联报销id
	private Integer orderId;		// 订单id
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
