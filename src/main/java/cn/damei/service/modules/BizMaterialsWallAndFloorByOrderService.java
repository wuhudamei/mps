package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsWallAndFloorByOrderDao;
import cn.damei.entity.modules.BizMaterialsWallAndFloorByOrder;

@Service
@Transactional(readOnly = true)
public class BizMaterialsWallAndFloorByOrderService extends CrudService2<BizMaterialsWallAndFloorByOrderDao, BizMaterialsWallAndFloorByOrder> {

	public List<BizMaterialsWallAndFloorByOrder> findList(BizMaterialsWallAndFloorByOrder bizMaterialsWallAndFloorByOrder) {
		return super.findList(bizMaterialsWallAndFloorByOrder);
	}
	
	public Page<BizMaterialsWallAndFloorByOrder> findPage(Page<BizMaterialsWallAndFloorByOrder> page, BizMaterialsWallAndFloorByOrder bizMaterialsWallAndFloorByOrder) {
		return super.findPage(page, bizMaterialsWallAndFloorByOrder);
	}

	public BizMaterialsWallAndFloorByOrder findByOrderId(Integer orderId) {
		return dao.findByOrderId(orderId);
	}

	public List<BizMaterialsWallAndFloorByOrder> findHejiByOrderId(Integer orderId) {
		return dao.findHejiByOrderId(orderId);
	}

	public List<BizMaterialsWallAndFloorByOrder> findMingxiByOrderId(Integer orderId) {
		return dao.findMingxiByOrderId(orderId);
	}

    public List<Dict> findPurchaseStatus() {
		return dao.findPurchaseStatus();
    }
}