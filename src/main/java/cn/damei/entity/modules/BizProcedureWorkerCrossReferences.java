/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工序和工人星级对照Entity
 * @author chy
 * @version 2016-09-17
 */
public class BizProcedureWorkerCrossReferences extends DataEntity<BizProcedureWorkerCrossReferences> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店
	private String taskPackageId;		// 任务包id
	private String procedureNo;		// 工序编号
	private String workerGroupStar;		// 工人组星级
	private String settlementRate;		// 结算比率
	private String isEnable;		// 启用标记
	
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