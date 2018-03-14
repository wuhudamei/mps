
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.dao.modules.BizQcStarCommissionCnfgSnapDao;


@Service
@Transactional(readOnly = true)
public class BizQcStarCommissionCnfgSnapService extends CrudService2<BizQcStarCommissionCnfgSnapDao, BizQcStarCommissionCnfgSnap> {

	public BizQcStarCommissionCnfgSnap get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcStarCommissionCnfgSnap> findList(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap) {
		return super.findList(bizQcStarCommissionCnfgSnap);
	}
	
	public Page<BizQcStarCommissionCnfgSnap> findPage(Page<BizQcStarCommissionCnfgSnap> page, BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap) {
		return super.findPage(page, bizQcStarCommissionCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap) {
		super.save(bizQcStarCommissionCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap) {
		super.delete(bizQcStarCommissionCnfgSnap);
	}

	public BizQcStarCommissionCnfgSnap findBqsccsByOrderId(Integer orderId) {

		return dao.findBqsccsByOrderId(orderId);
	}
	
}