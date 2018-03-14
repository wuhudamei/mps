package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ReceivedPanelDao;
import cn.damei.entity.mobile.Manager.ReceivedPanel;

@Service
@Transactional(readOnly = true)
public class ReceivedPanelService extends CrudService2<ReceivedPanelDao, ReceivedPanel>{

	public List<ReceivedPanel> queryPanelByPurchaseId(Integer purchaseId) {
		List<ReceivedPanel> list = dao.queryPanelByPurchaseId(purchaseId);
		return list;
	}

	public List<ReceivedPanel> queryPanelByReceiveBillId(Integer id) {

		return dao.queryPanelByReceiveBillId(id);
	}

}
