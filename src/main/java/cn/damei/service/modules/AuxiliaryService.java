
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.dao.modules.AuxiliaryDao;


@Service
@Transactional(readOnly = true)
public class AuxiliaryService extends CrudService2<AuxiliaryDao, Auxiliary> {

	public List<Auxiliary> findListByPurchaseId(Integer purchaseId,Integer storeId) {
		
		return dao.findListByPurchaseId(purchaseId,storeId);
	}

	public List<BizSupplier> findSuppliersByPurchaseId(Integer purchaseId, Integer storeId) {

		return dao.findSuppliersByPurchaseId(purchaseId,storeId);
	}

	public List<Auxiliary> findListPriceByPurchaseId(Integer id, Integer storeId, Date date) {

		return dao.findListPriceByPurchaseId(id,storeId,date);
	}

	
	
	
	
}