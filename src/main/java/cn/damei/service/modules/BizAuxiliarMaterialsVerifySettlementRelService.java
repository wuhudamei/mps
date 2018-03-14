package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAuxiliarMaterialsVerifySettlementRelDao;
import cn.damei.entity.modules.BizAuxiliarMaterialsVerifySettlementRel;

/**
 * 对账单结算关系Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizAuxiliarMaterialsVerifySettlementRelService extends CrudService2<BizAuxiliarMaterialsVerifySettlementRelDao, BizAuxiliarMaterialsVerifySettlementRel>{

	public BizAuxiliarMaterialsVerifySettlementRel get(Integer id) {
		return super.get(id);
	}


	public List<BizAuxiliarMaterialsVerifySettlementRel> findList(BizAuxiliarMaterialsVerifySettlementRel bizAuxiliarMaterialsVerifySettlementRel) {
		return super.findList(bizAuxiliarMaterialsVerifySettlementRel);
	}

	public Page<BizAuxiliarMaterialsVerifySettlementRel> findPage(Page<BizAuxiliarMaterialsVerifySettlementRel> page, BizAuxiliarMaterialsVerifySettlementRel bizAuxiliarMaterialsVerifySettlementRel) {
		return super.findPage(page, bizAuxiliarMaterialsVerifySettlementRel);
	}


	@Transactional(readOnly = false)
	public void save(BizAuxiliarMaterialsVerifySettlementRel bizAuxiliarMaterialsVerifySettlementRel) {
		super.save(bizAuxiliarMaterialsVerifySettlementRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizAuxiliarMaterialsVerifySettlementRel bizAuxiliarMaterialsVerifySettlementRel){
		super.delete(bizAuxiliarMaterialsVerifySettlementRel);
	}
}
