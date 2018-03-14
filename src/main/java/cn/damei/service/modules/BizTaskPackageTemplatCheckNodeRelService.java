/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizTaskPackageTemplatCheckNodeRel;
import cn.damei.dao.modules.BizTaskPackageTemplatCheckNodeRelDao;

/**
 * 付款单付款尾款节点设置Service
 * @author www
 * @version 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class BizTaskPackageTemplatCheckNodeRelService extends CrudService2<BizTaskPackageTemplatCheckNodeRelDao, BizTaskPackageTemplatCheckNodeRel> {

	public BizTaskPackageTemplatCheckNodeRel get(Integer id) {
		return super.get(id);
	}
	
	public List<BizTaskPackageTemplatCheckNodeRel> findList(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel) {
		return super.findList(bizTaskPackageTemplatCheckNodeRel);
	}
	
	public Page<BizTaskPackageTemplatCheckNodeRel> findPage(Page<BizTaskPackageTemplatCheckNodeRel> page, BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel) {
		return super.findPage(page, bizTaskPackageTemplatCheckNodeRel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel) {
		super.save(bizTaskPackageTemplatCheckNodeRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel) {
		super.delete(bizTaskPackageTemplatCheckNodeRel);
	}
	
	@Transactional(readOnly = false)
	public void updateStatus(Integer id, String status) {
		
		dao.updateStatus(id,status);
	}
	
	@Transactional(readOnly = false)
	public void insert(BizTaskPackageTemplatCheckNodeRel nodeRel) {
		nodeRel.preInsert();
		dao.insert(nodeRel);
	}
}