
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmGuaranteeMoneyCnfgSnapDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;


@Service
@Transactional(readOnly = true)
public class BizPmGuaranteeMoneyCnfgSnapService extends CrudService2<BizPmGuaranteeMoneyCnfgSnapDao, BizPmGuaranteeMoneyCnfgSnap> {

	public BizPmGuaranteeMoneyCnfgSnap get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmGuaranteeMoneyCnfgSnap> findList(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap) {
		return super.findList(bizPmGuaranteeMoneyCnfgSnap);
	}
	
	public Page<BizPmGuaranteeMoneyCnfgSnap> findPage(Page<BizPmGuaranteeMoneyCnfgSnap> page, BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap) {
		return super.findPage(page, bizPmGuaranteeMoneyCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap) {
		super.save(bizPmGuaranteeMoneyCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap) {
		super.delete(bizPmGuaranteeMoneyCnfgSnap);
	}

	public BizPmGuaranteeMoneyCnfgSnap findGmc(Integer orderId) {
		
		return dao.findGmc(orderId);
	}
	
}