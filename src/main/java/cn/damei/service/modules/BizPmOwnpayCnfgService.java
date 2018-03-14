/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmOwnpayCnfgDao;
import cn.damei.entity.modules.BizPmOwnpayCnfg;

/**
 * 自主支配项定义Service
 * @author wyb
 * @version 2016-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizPmOwnpayCnfgService extends CrudService2<BizPmOwnpayCnfgDao, BizPmOwnpayCnfg> {

	public BizPmOwnpayCnfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmOwnpayCnfg> findList(BizPmOwnpayCnfg bizPmOwnpayCnfg) {
		return super.findList(bizPmOwnpayCnfg);
	}
	
	public Page<BizPmOwnpayCnfg> findPage(Page<BizPmOwnpayCnfg> page, BizPmOwnpayCnfg bizPmOwnpayCnfg) {
		return super.findPage(page, bizPmOwnpayCnfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmOwnpayCnfg bizPmOwnpayCnfg) {
		super.save(bizPmOwnpayCnfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmOwnpayCnfg bizPmOwnpayCnfg) {
		super.delete(bizPmOwnpayCnfg);
	}
	
}