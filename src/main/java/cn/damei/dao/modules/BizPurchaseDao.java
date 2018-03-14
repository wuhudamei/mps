
package cn.damei.dao.modules;

import java.util.Date;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPurchase;


@MyBatisDao
public interface BizPurchaseDao extends CrudDao2<BizPurchase> {

	void updateStatusById(Integer id);

	void updateStatus1ById(int id, String status,Date date);

	void updateStatus2ById(int parseInt, String status);


	boolean updateMainPanelStatus(BizPurchase bizPurchase);


	Integer findSettlementIsExist(Integer orderId);
	
}