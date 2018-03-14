
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.dao.modules.BizQcLongwayCommissionCnfgSnapDao;


@Service
@Transactional(readOnly = true)
public class BizQcLongwayCommissionCnfgSnapService extends CrudService2<BizQcLongwayCommissionCnfgSnapDao, BizQcLongwayCommissionCnfgSnap> {

	public BizQcLongwayCommissionCnfgSnap get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcLongwayCommissionCnfgSnap> findList(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap) {
		return super.findList(bizQcLongwayCommissionCnfgSnap);
	}
	
	public Page<BizQcLongwayCommissionCnfgSnap> findPage(Page<BizQcLongwayCommissionCnfgSnap> page, BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap) {
		return super.findPage(page, bizQcLongwayCommissionCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap) {
		super.save(bizQcLongwayCommissionCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap) {
		super.delete(bizQcLongwayCommissionCnfgSnap);
	}

	public BizQcLongwayCommissionCnfgSnap findBqlccsByOrderId(Integer orderId) {

		return dao.findBqlccsByOrderId(orderId);
	}
}