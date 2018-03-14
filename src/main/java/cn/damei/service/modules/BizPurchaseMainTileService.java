package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPurchaseMainTileDao;
import cn.damei.entity.modules.BizPurchaseMainTile;

@Service
@Transactional(readOnly = true)
public class BizPurchaseMainTileService extends CrudService2<BizPurchaseMainTileDao,BizPurchaseMainTile>{
	
	public BizPurchaseMainTile get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPurchaseMainTile> findList(BizPurchaseMainTile bizPurchaseMainTile) {
		return super.findList(bizPurchaseMainTile);
	}
	
	public Page<BizPurchaseMainTile> findPage(Page<BizPurchaseMainTile> page, BizPurchaseMainTile bizPurchaseMainTile) {
		return super.findPage(page, bizPurchaseMainTile);
	}

	public BizPurchaseMainTile findById(Integer id) {

		return dao.get(id);
	}

	public void updateStatusById(Integer id) {
		
		dao.updateStatusById(id);
	}

	public List<BizPurchaseMainTile> findList1(Integer applyemployeeId,String type) {

		return dao.findList1(applyemployeeId,type);
	}
	
}
