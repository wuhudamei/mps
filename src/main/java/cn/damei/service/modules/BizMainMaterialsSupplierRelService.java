/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMainMaterialsSupplierRel;
import cn.damei.dao.modules.BizMainMaterialsSupplierRelDao;

/**
 * 主材供应商管理Service
 * @author qww
 * @version 2016-10-11
 */
@Service
@Transactional(readOnly = true)
public class BizMainMaterialsSupplierRelService extends CrudService2<BizMainMaterialsSupplierRelDao, BizMainMaterialsSupplierRel> {
	
	public BizMainMaterialsSupplierRel get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMainMaterialsSupplierRel> findList(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel) {
		return super.findList(bizMainMaterialsSupplierRel);
	}
	
	public Page<BizMainMaterialsSupplierRel> findPage(Page<BizMainMaterialsSupplierRel> page, BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel) {
		return super.findPage(page, bizMainMaterialsSupplierRel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel) {
		super.save(bizMainMaterialsSupplierRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel) {
		super.delete(bizMainMaterialsSupplierRel);
	}
	
	@Transactional(readOnly = false)
	public Integer getMaxVersion(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel){
		return this.dao.getMaxVersion(bizMainMaterialsSupplierRel);
	}
}