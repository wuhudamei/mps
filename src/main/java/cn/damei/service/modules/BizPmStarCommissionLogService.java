
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPmStarCommissionLog;
import cn.damei.dao.modules.BizPmStarCommissionLogDao;


@Service
@Transactional(readOnly = true)
public class BizPmStarCommissionLogService extends CrudService2<BizPmStarCommissionLogDao, BizPmStarCommissionLog> {

	public BizPmStarCommissionLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmStarCommissionLog> findList(BizPmStarCommissionLog bizPmStarCommissionLog) {
		return super.findList(bizPmStarCommissionLog);
	}
	
	public Page<BizPmStarCommissionLog> findPage(Page<BizPmStarCommissionLog> page, BizPmStarCommissionLog bizPmStarCommissionLog) {
		return super.findPage(page, bizPmStarCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmStarCommissionLog bizPmStarCommissionLog) {
		super.save(bizPmStarCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmStarCommissionLog bizPmStarCommissionLog) {
		super.delete(bizPmStarCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public Integer insert1(BizPmStarCommissionLog bizPmStarCommissionLog) {
		return dao.insert1(bizPmStarCommissionLog);
	}
	
}