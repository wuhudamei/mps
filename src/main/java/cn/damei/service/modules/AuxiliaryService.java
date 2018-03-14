/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.dao.modules.AuxiliaryDao;

/**
 * 采购单Service
 * @author 汪文文
 * @version 2016-09-28
 */
@Service
@Transactional(readOnly = true)
public class AuxiliaryService extends CrudService2<AuxiliaryDao, Auxiliary> {

	public List<Auxiliary> findListByPurchaseId(Integer purchaseId,Integer storeId) {
		
		return dao.findListByPurchaseId(purchaseId,storeId);
	}

	public List<BizSupplier> findSuppliersByPurchaseId(Integer purchaseId, Integer storeId) {
		// TODO Auto-generated method stub
		return dao.findSuppliersByPurchaseId(purchaseId,storeId);
	}

	public List<Auxiliary> findListPriceByPurchaseId(Integer id, Integer storeId, Date date) {
		// TODO Auto-generated method stub
		return dao.findListPriceByPurchaseId(id,storeId,date);
	}

	
	
	
	
}