package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.MainPanelDao;
import cn.damei.entity.modules.MainPanel;

@Service
@Transactional(readOnly = true)
public class MainPanelService extends CrudService2<MainPanelDao, MainPanel>{

	public List<MainPanel> findListByPurchaseId(Integer id) {
		// TODO Auto-generated method stub
		return dao.findListByPurchaseId(id);
	}


}
