package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderMaterialCarryCostDao;
import cn.damei.entity.modules.BizOrderMaterialCarryCost;

/**
 *订单材料搬运运输费Service 
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizOrderMaterialCarryCostService extends CrudService2<BizOrderMaterialCarryCostDao, BizOrderMaterialCarryCost>{

	@Transactional(readOnly = false)
	public void save(BizOrderMaterialCarryCost bizOrderMaterialCarryCost) {
		super.save(bizOrderMaterialCarryCost);
	}
	
	public BizOrderMaterialCarryCost queryOrderMaterialCarryCostByOrderId(Integer orderId){
		return dao.queryOrderMaterialCarryCostByOrderId(orderId);
	}
}
