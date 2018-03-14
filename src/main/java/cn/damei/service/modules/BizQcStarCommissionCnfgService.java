/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcStarCommissionCnfg;
import cn.damei.dao.modules.BizQcStarCommissionCnfgDao;

/**
 * 质检员星级和提成设置Service
 * @author wyb
 * @version 2017-02-13
 */
@Service
@Transactional(readOnly = true)
public class BizQcStarCommissionCnfgService extends CrudService2<BizQcStarCommissionCnfgDao, BizQcStarCommissionCnfg> {

	public BizQcStarCommissionCnfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcStarCommissionCnfg> findList(BizQcStarCommissionCnfg bizQcStarCommissionCnfg) {
		return super.findList(bizQcStarCommissionCnfg);
	}
	
	public Page<BizQcStarCommissionCnfg> findPage(Page<BizQcStarCommissionCnfg> page, BizQcStarCommissionCnfg bizQcStarCommissionCnfg) {
		return super.findPage(page, bizQcStarCommissionCnfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcStarCommissionCnfg bizQcStarCommissionCnfg) {
		super.save(bizQcStarCommissionCnfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcStarCommissionCnfg bizQcStarCommissionCnfg) {
		super.delete(bizQcStarCommissionCnfg);
	}
	
}