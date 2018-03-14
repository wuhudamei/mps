package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.MainPanel;

@MyBatisDao
public interface MainPanelDao extends CrudDao2<MainPanel>{

	List<MainPanel> findListByPurchaseId(Integer id);

}
