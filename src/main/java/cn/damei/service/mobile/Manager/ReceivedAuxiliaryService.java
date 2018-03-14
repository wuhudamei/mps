package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ReceivedAuxiliaryDao;
import cn.damei.entity.mobile.Manager.ReceivedAuxiliary;

@Service
@Transactional(readOnly = true)
public class ReceivedAuxiliaryService extends CrudService2<ReceivedAuxiliaryDao, ReceivedAuxiliary>{

	public List<ReceivedAuxiliary> queryAuxiliaryByPurchase(Integer purchaseId) {
		return dao.queryAuxiliaryByPurchase(purchaseId);
	}

	public List<ReceivedAuxiliary> queryAuxiliaryByReceiveBillId(Integer id) {
		return dao.queryAuxiliaryByReceiveBillId(id);
	}
	
}
