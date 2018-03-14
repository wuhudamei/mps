/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfg;
import cn.damei.dao.modules.BizQcLongwayCommissionCnfgDao;

/**
 * 远程费提成金额设置Service
 * @author wyb
 * @version 2017-02-13
 */
@Service
@Transactional(readOnly = true)
public class BizQcLongwayCommissionCnfgService extends CrudService2<BizQcLongwayCommissionCnfgDao, BizQcLongwayCommissionCnfg> {

	public BizQcLongwayCommissionCnfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcLongwayCommissionCnfg> findList(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg) {
		return super.findList(bizQcLongwayCommissionCnfg);
	}
	
	public Page<BizQcLongwayCommissionCnfg> findPage(Page<BizQcLongwayCommissionCnfg> page, BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg) {
		return super.findPage(page, bizQcLongwayCommissionCnfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg) {
		super.save(bizQcLongwayCommissionCnfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg) {
		super.delete(bizQcLongwayCommissionCnfg);
	}
	
}