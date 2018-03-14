/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmGuaranteeMoneyCnfgDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfg;

/**
 * 项目经理质保金设置Service
 * @author wyb
 * @version 2016-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizPmGuaranteeMoneyCnfgService extends CrudService2<BizPmGuaranteeMoneyCnfgDao, BizPmGuaranteeMoneyCnfg> {

	public BizPmGuaranteeMoneyCnfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmGuaranteeMoneyCnfg> findList(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg) {
		return super.findList(bizPmGuaranteeMoneyCnfg);
	}
	
	public Page<BizPmGuaranteeMoneyCnfg> findPage(Page<BizPmGuaranteeMoneyCnfg> page, BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg) {
		return super.findPage(page, bizPmGuaranteeMoneyCnfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg) {
		super.save(bizPmGuaranteeMoneyCnfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg) {
		super.delete(bizPmGuaranteeMoneyCnfg);
	}
	
}