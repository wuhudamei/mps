package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardReceiveBillVoDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillVo;

@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardReceiveBillVoService extends CrudService2<BizMaterialsStandardReceiveBillVoDao, BizMaterialsStandardReceiveBillVo>{
	
	public BizMaterialsStandardReceiveBillVo get(Integer id) {
		return super.get(id);
	}
	
	@Transactional(readOnly = false)
	public void insert(BizMaterialsStandardReceiveBillVo bizMaterialsStandardReceiveBill) {
		dao.insert(bizMaterialsStandardReceiveBill);
	}

	public BizMaterialsStandardReceiveBillVo findByCode(String code) {

		return dao.findByCode(code);
	}

	public BizMaterialsStandardReceiveBillVo findBySettleBillId(Integer billId) {

		return dao.findBySettleBillId(billId);
	}

	public BizMaterialsStandardReceiveBillVo findByOrderId(Integer orderId) {

		return dao.findByOrderId(orderId);
	}
}
