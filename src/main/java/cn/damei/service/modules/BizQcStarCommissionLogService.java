
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcStarCommissionLog;
import cn.damei.dao.modules.BizQcStarCommissionLogDao;


@Service
@Transactional(readOnly = true)
public class BizQcStarCommissionLogService extends CrudService2<BizQcStarCommissionLogDao, BizQcStarCommissionLog> {

	public BizQcStarCommissionLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcStarCommissionLog> findList(BizQcStarCommissionLog bizQcStarCommissionLog) {
		return super.findList(bizQcStarCommissionLog);
	}
	
	public Page<BizQcStarCommissionLog> findPage(Page<BizQcStarCommissionLog> page, BizQcStarCommissionLog bizQcStarCommissionLog) {
		return super.findPage(page, bizQcStarCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcStarCommissionLog bizQcStarCommissionLog) {
		super.save(bizQcStarCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcStarCommissionLog bizQcStarCommissionLog) {
		super.delete(bizQcStarCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public Integer insert1(BizQcStarCommissionLog bizQcStarCommissionLog) {
		return dao.insert1(bizQcStarCommissionLog);
	}
	
}