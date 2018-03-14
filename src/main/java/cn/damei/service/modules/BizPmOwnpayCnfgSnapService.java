/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmOwnpayCnfgSnapDao;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;

/**
 * 自主支配快照Service
 * @author 汪文文
 * @version 2016-12-28
 */
@Service
@Transactional(readOnly = true)
public class BizPmOwnpayCnfgSnapService extends CrudService2<BizPmOwnpayCnfgSnapDao, BizPmOwnpayCnfgSnap> {

	public BizPmOwnpayCnfgSnap get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmOwnpayCnfgSnap> findList(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap) {
		return super.findList(bizPmOwnpayCnfgSnap);
	}
	
	public Page<BizPmOwnpayCnfgSnap> findPage(Page<BizPmOwnpayCnfgSnap> page, BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap) {
		return super.findPage(page, bizPmOwnpayCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap) {
		super.save(bizPmOwnpayCnfgSnap);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap) {
		super.delete(bizPmOwnpayCnfgSnap);
	}
	
}