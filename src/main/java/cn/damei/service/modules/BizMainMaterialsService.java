/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMainMaterials;
import cn.damei.dao.modules.BizMainMaterialsDao;

/**
 * 主材管理Service
 * @author qww
 * @version 2016-10-10
 */
@Service
@Transactional(readOnly = true)
public class BizMainMaterialsService extends CrudService2<BizMainMaterialsDao, BizMainMaterials> {

	public BizMainMaterials get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMainMaterials> findList(BizMainMaterials bizMainMaterials) {
		return super.findList(bizMainMaterials);
	}
	
	public Page<BizMainMaterials> findPage(Page<BizMainMaterials> page, BizMainMaterials bizMainMaterials) {
		return super.findPage(page, bizMainMaterials);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMainMaterials bizMainMaterials) {
		super.save(bizMainMaterials);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMainMaterials bizMainMaterials) {
		super.delete(bizMainMaterials);
	}
	
}