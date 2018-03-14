package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.GuaranteeDao;
import cn.damei.entity.modules.Guarantee;

@Service
@Transactional(readOnly = true)
public class GuaranteeService extends CrudService2<GuaranteeDao, Guarantee>{

	public Guarantee findGuaranteeBySettlementId(Integer id) {

		return dao.findGuaranteeBySettlementId(id);
	}

}
