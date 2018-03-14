
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmSettleCheckNodeCnfgDao;
import cn.damei.entity.modules.BizPmSettleCheckNodeCnfg;


@Service
@Transactional(readOnly = true)
public class BizPmSettleCheckNodeCnfgService extends CrudService2<BizPmSettleCheckNodeCnfgDao, BizPmSettleCheckNodeCnfg> {

	public BizPmSettleCheckNodeCnfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmSettleCheckNodeCnfg> findList(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg) {
		return super.findList(bizPmSettleCheckNodeCnfg);
	}
	
	public Page<BizPmSettleCheckNodeCnfg> findPage(Page<BizPmSettleCheckNodeCnfg> page, BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg) {
		return super.findPage(page, bizPmSettleCheckNodeCnfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg) {
		super.save(bizPmSettleCheckNodeCnfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg) {
		super.delete(bizPmSettleCheckNodeCnfg);
	}
	
}