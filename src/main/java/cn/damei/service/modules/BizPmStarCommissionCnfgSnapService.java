/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmStarCommissionCnfgSnapDao;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;

/**
 * 项目经理星级提成快照Service
 * @author 汪文文
 * @version 2016-12-28
 */
@Service
@Transactional(readOnly = true)
public class BizPmStarCommissionCnfgSnapService extends CrudService2<BizPmStarCommissionCnfgSnapDao, BizPmStarCommissionCnfgSnap> {

	public BizPmStarCommissionCnfgSnap get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmStarCommissionCnfgSnap> findList(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap) {
		return super.findList(bizPmStarCommissionCnfgSnap);
	}
	
	public Page<BizPmStarCommissionCnfgSnap> findPage(Page<BizPmStarCommissionCnfgSnap> page, BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap) {
		return super.findPage(page, bizPmStarCommissionCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap) {
		super.save(bizPmStarCommissionCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap) {
		super.delete(bizPmStarCommissionCnfgSnap);
	}

	public BizPmStarCommissionCnfgSnap findSccs(Integer orderId) {
		// TODO Auto-generated method stub
		return dao.findSccs(orderId);
	}
	
}