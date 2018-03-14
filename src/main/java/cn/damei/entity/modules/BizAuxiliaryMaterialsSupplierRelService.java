/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizAuxiliaryMaterialsSupplierRel;
import cn.damei.dao.modules.BizAuxiliaryMaterialsSupplierRelDao;

/**
 * 辅料对应供应商Service
 * @author chy
 * @version 2016-09-09
 */
@Service
@Transactional(readOnly = true)
public class BizAuxiliaryMaterialsSupplierRelService extends CrudService<BizAuxiliaryMaterialsSupplierRelDao, BizAuxiliaryMaterialsSupplierRel> {

	public BizAuxiliaryMaterialsSupplierRel get(String id) {
		return super.get(id);
	}
	public Integer getMaxVersion(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel){
		return this.dao.getMaxVersion(bizAuxiliaryMaterialsSupplierRel);
	}
	
	public List<BizAuxiliaryMaterialsSupplierRel> findList(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		return super.findList(bizAuxiliaryMaterialsSupplierRel);
	}
	
	public Page<BizAuxiliaryMaterialsSupplierRel> findPage(Page<BizAuxiliaryMaterialsSupplierRel> page, BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		return super.findPage(page, bizAuxiliaryMaterialsSupplierRel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		super.save(bizAuxiliaryMaterialsSupplierRel);
	}
	
	
	@Transactional(readOnly = false)
	public void saveWz(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		if (bizAuxiliaryMaterialsSupplierRel.getIsNewRecord()) {
			bizAuxiliaryMaterialsSupplierRel.preInsert();
			dao.insertWz(bizAuxiliaryMaterialsSupplierRel);
		} else {
			bizAuxiliaryMaterialsSupplierRel.preUpdate();
			dao.updateWz(bizAuxiliaryMaterialsSupplierRel);
		}
	}
	
	@Transactional(readOnly = false)
	public void saveSupplier(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		if (bizAuxiliaryMaterialsSupplierRel.getIsNewRecord()) {
			bizAuxiliaryMaterialsSupplierRel.preInsert();
			dao.insertSupplier(bizAuxiliaryMaterialsSupplierRel);
		} else {
			bizAuxiliaryMaterialsSupplierRel.preUpdate();
			dao.updateSupplier(bizAuxiliaryMaterialsSupplierRel);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		super.delete(bizAuxiliaryMaterialsSupplierRel);
	}
	public List<Integer> findSupplierName(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel) {
		// TODO Auto-generated method stub
		return this.dao.findSupplierName(bizAuxiliaryMaterialsSupplierRel);
	}
	
}