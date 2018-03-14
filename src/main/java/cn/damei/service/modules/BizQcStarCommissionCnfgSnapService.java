/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.dao.modules.BizQcStarCommissionCnfgSnapDao;

/**
 * 质检员星级提成快照Service
 * @author 汪文文
 * @version 2017-02-13
 */
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
		// TODO Auto-generated method stub
		return dao.findBqsccsByOrderId(orderId);
	}
	
}