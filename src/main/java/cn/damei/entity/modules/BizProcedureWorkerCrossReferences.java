
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizProcedureWorkerCrossReferences extends DataEntity<BizProcedureWorkerCrossReferences> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String taskPackageId;
	private String procedureNo;
	private String workerGroupStar;
	private String settlementRate;
	private String isEnable;
	
	public BizProcedureWorkerCrossReferences() {
		super();
	}

	public BizProcedureWorkerCrossReferences(String id){
		super(id);
	}

	@Length(min=1, max=64, message="门店长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=11, message="任务包id长度必须介于 1 和 11 之间")
	public String getTaskPackageId() {
		return taskPackageId;
	}

	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	
	@Length(min=1, max=100, message="工序编号长度必须介于 1 和 100 之间")
	public String getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}
	
	@Length(min=1, max=80, message="工人组星级长度必须介于 1 和 80 之间")
	public String getWorkerGroupStar() {
		return workerGroupStar;
	}

	public void setWorkerGroupStar(String workerGroupStar) {
		this.workerGroupStar = workerGroupStar;
	}
	
	public String getSettlementRate() {
		if(settlementRate!=null){
			return settlementRate.replaceAll("\\.00", "");
		}
		return settlementRate;
	}

	public void setSettlementRate(String settlementRate) {
		this.settlementRate = settlementRate;
	}
	
	@Length(min=1, max=1, message="启用标记长度必须介于 1 和 1 之间")
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
}