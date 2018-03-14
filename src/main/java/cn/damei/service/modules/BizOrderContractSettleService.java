package cn.damei.service.modules;

import java.util.Map;

import cn.damei.entity.modules.BizOrderContractSettle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderContractSettleDao;

/**
 * 订单承包结算Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizOrderContractSettleService extends CrudService2<BizOrderContractSettleDao, BizOrderContractSettle>{

	@Transactional(readOnly = false)
	public void save(BizOrderContractSettle bizOrderContractSettle) {
		super.save(bizOrderContractSettle);
	}
	
	public BizOrderContractSettle findOrderContractSettleByParam(Map<String,Object> param){
		return dao.findOrderContractSettleByParam(param);
	}
}
