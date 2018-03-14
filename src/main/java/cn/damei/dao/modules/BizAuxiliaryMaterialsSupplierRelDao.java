/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAuxiliaryMaterialsSupplierRel;

/**
 * 辅料对应供应商DAO接口
 * @author chy
 * @version 2016-09-09
 */
@MyBatisDao
public interface BizAuxiliaryMaterialsSupplierRelDao extends CrudDao<BizAuxiliaryMaterialsSupplierRel> {
	public Integer getMaxVersion(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel);

	public List<Integer> findSupplierName(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel);

	public void insertWz(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel);

	public void updateWz(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel);

	public void insertSupplier(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel);

	public void updateSupplier(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel);
}