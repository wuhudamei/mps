package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizAuxiliarMaterialsVerifySettlementRel extends DataEntity2<BizAuxiliarMaterialsVerifySettlementRel> {

	private static final long serialVersionUID = 1L;

	private Integer auxiliaryMaterialsVerifyId;
	private Integer orderTaskpackageSettlementId;

	public Integer getAuxiliaryMaterialsVerifyId() {
		return auxiliaryMaterialsVerifyId;
	}

	public void setAuxiliaryMaterialsVerifyId(Integer auxiliaryMaterialsVerifyId) {
		this.auxiliaryMaterialsVerifyId = auxiliaryMaterialsVerifyId;
	}

	public Integer getOrderTaskpackageSettlementId() {
		return orderTaskpackageSettlementId;
	}

	public void setOrderTaskpackageSettlementId(Integer orderTaskpackageSettlementId) {
		this.orderTaskpackageSettlementId = orderTaskpackageSettlementId;
	}

}
