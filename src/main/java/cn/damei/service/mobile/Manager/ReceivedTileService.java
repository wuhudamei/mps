package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ReceivedTileDao;
import cn.damei.entity.mobile.Manager.ReceivedTile;

@Service
@Transactional(readOnly = true)
public class ReceivedTileService extends CrudService2<ReceivedTileDao, ReceivedTile>{

	public List<ReceivedTile> queryTileByPurchaseId(Integer purchaseId) {
		List<ReceivedTile> list = dao.queryTileByPurchaseId(purchaseId);
		return list;
	}

	public List<ReceivedTile> queryTileByReceiveBillId(Integer id) {
		// TODO Auto-generated method stub
		return dao.queryTileByReceiveBillId(id);
	}

}
