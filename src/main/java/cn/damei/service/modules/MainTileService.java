package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.MainTileDao;
import cn.damei.entity.modules.MainTile;

@Service
@Transactional(readOnly = true)
public class MainTileService extends CrudService2<MainTileDao, MainTile>{

	public List<MainTile> findListByPurchaseId(Integer purchaseId) {
		
		return dao.findListByPurchaseId(purchaseId);
	}
	
}
