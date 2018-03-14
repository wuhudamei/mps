package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.SettlementForDetailDao;
import cn.damei.entity.modules.SettlementForDetail;
@Service
@Transactional
public class SettlementForDetailService extends CrudService2<SettlementForDetailDao, SettlementForDetail>{
	
	public Page<SettlementForDetail> findPage(Page<SettlementForDetail> page, SettlementForDetail settlementForDetail) {
		return super.findPage(page, settlementForDetail);
	}
}
