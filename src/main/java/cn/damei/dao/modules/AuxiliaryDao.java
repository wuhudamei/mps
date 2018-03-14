/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizSupplier;

/**
 * 采购单DAO接口
 * @author 汪文文
 * @version 2016-09-28
 */
@MyBatisDao
public interface AuxiliaryDao extends CrudDao2<Auxiliary> {

	List<Auxiliary> findListByPurchaseId(Integer purchaseId,Integer storeId);

	List<BizSupplier> findSuppliersByPurchaseId(Integer purchaseId, Integer storeId);

	List<Auxiliary> findListPriceByPurchaseId(Integer id, Integer storeId, Date date);
	
}