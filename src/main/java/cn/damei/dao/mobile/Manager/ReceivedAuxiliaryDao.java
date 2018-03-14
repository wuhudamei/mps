package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ReceivedAuxiliary;

@MyBatisDao
public interface ReceivedAuxiliaryDao extends CrudDao2<ReceivedAuxiliary>{

	List<ReceivedAuxiliary> queryAuxiliaryByPurchase(Integer purchaseId);

	void updateCount(int parseInt, Double receiveCount,Double owedCount);

	ReceivedAuxiliary queryAuxiliaryById(int id);

	List<ReceivedAuxiliary> queryAuxiliaryByReceiveBillId(Integer id);
	
}
