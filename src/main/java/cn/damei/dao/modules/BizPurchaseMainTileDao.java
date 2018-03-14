package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPurchaseMainTile;

@MyBatisDao
public interface BizPurchaseMainTileDao extends CrudDao2<BizPurchaseMainTile>{

	void updateStatusById(Integer id);

	List<BizPurchaseMainTile> findList1(Integer applyemployeeId, String type);
	
}
