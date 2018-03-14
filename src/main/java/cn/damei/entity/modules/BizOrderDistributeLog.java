
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;


public class BizOrderDistributeLog extends DataEntity2<BizOrderDistributeLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		
	private Integer orderTaskpackageId;		
	private String distributeType;		
	private Integer distributedEmployeeId;
	private String distributedEmployeeName;
	private Integer distributedGroupId;		
	private Integer unfinishedOrderCountBefore;	
	private Integer distributeOrderCount;		
	private Integer unfinishedOrderCountAfter;	

	private Integer oldEmployeeId;

	private String reasonType;

	private String reasonRemarks;
	
	
	
	public String getDistributedEmployeeName() {
		return distributedEmployeeName;
	}

	public void setDistributedEmployeeName(String distributedEmployeeName) {
		this.distributedEmployeeName = distributedEmployeeName;
	}

	public BizOrderDistributeLog() {
		super();
	}

	public BizOrderDistributeLog(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}

	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}

	public String getDistributeType() {
		return distributeType;
	}

	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}

	public Integer getDistributedEmployeeId() {
		return distributedEmployeeId;
	}

	public void setDistributedEmployeeId(Integer distributedEmployeeId) {
		this.distributedEmployeeId = distributedEmployeeId;
	}

	public Integer getDistributedGroupId() {
		return distributedGroupId;
	}

	public void setDistributedGroupId(Integer distributedGroupId) {
		this.distributedGroupId = distributedGroupId;
	}

	public Integer getUnfinishedOrderCountBefore() {
		return unfinishedOrderCountBefore;
	}

	public void setUnfinishedOrderCountBefore(Integer unfinishedOrderCountBefore) {
		this.unfinishedOrderCountBefore = unfinishedOrderCountBefore;
	}

	public Integer getDistributeOrderCount() {
		return distributeOrderCount;
	}

	public void setDistributeOrderCount(Integer distributeOrderCount) {
		this.distributeOrderCount = distributeOrderCount;
	}

	public Integer getUnfinishedOrderCountAfter() {
		return unfinishedOrderCountAfter;
	}

	public void setUnfinishedOrderCountAfter(Integer unfinishedOrderCountAfter) {
		this.unfinishedOrderCountAfter = unfinishedOrderCountAfter;
	}

	public Integer getOldEmployeeId() {
		return oldEmployeeId;
	}

	public void setOldEmployeeId(Integer oldEmployeeId) {
		this.oldEmployeeId = oldEmployeeId;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getReasonRemarks() {
		return reasonRemarks;
	}

	public void setReasonRemarks(String reasonRemarks) {
		this.reasonRemarks = reasonRemarks;
	}

}