package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ReceivedTile;

@MyBatisDao
public interface ReceivedTileDao extends CrudDao2<ReceivedTile>{

	List<ReceivedTile> queryTileByPurchaseId(Integer purchaseId);

	void updateCount(int parseInt,  Double receivedCount,Double owedCount);

	ReceivedTile queryTileById(int parseInt);

	List<ReceivedTile> queryTileByReceiveBillId(Integer id);

}
