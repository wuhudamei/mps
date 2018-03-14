
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmStarCommissionCnfgDao;
import cn.damei.entity.modules.BizPmStarCommissionCnfg;


@Service
@Transactional(readOnly = true)
public class BizPmStarCommissionCnfgService extends CrudService2<BizPmStarCommissionCnfgDao, BizPmStarCommissionCnfg> {

	public BizPmStarCommissionCnfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmStarCommissionCnfg> findList(BizPmStarCommissionCnfg bizPmStarCommissionCnfg) {
		return super.findList(bizPmStarCommissionCnfg);
	}
	
	public Page<BizPmStarCommissionCnfg> findPage(Page<BizPmStarCommissionCnfg> page, BizPmStarCommissionCnfg bizPmStarCommissionCnfg) {
		return super.findPage(page, bizPmStarCommissionCnfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmStarCommissionCnfg bizPmStarCommissionCnfg) {
		super.save(bizPmStarCommissionCnfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmStarCommissionCnfg bizPmStarCommissionCnfg) {
		super.delete(bizPmStarCommissionCnfg);
	}
	
}