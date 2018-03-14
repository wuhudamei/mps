package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 辅料对账单结算单关系实体类
 * 
 * @author hyh
 *
 */
public class BizAuxiliarMaterialsVerifySettlementRel extends DataEntity2<BizAuxiliarMaterialsVerifySettlementRel> {

	private static final long serialVersionUID = 1L;

	private Integer auxiliaryMaterialsVerifyId; // 对账单Id
	private Integer orderTaskpackageSettlementId;// 结算单Id

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
