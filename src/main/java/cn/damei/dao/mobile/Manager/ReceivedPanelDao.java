package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ReceivedPanel;

@MyBatisDao
public interface ReceivedPanelDao extends CrudDao2<ReceivedPanel>{

	List<ReceivedPanel> queryPanelByPurchaseId(Integer purchaseId);

	void updateCount(int parseInt, Double receivedCount,Double owedCount);

	ReceivedPanel queryPanelById(int parseInt);

	List<ReceivedPanel> queryPanelByReceiveBillId(Integer id);

}
