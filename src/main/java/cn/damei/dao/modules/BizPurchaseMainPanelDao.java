package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPurchaseMainPanel;

@MyBatisDao
public interface BizPurchaseMainPanelDao extends CrudDao2<BizPurchaseMainPanel>{

	List<BizPurchaseMainPanel> findList1(Integer applyemployeeId, String type);

    List<BizPurchaseMainPanel> queryPurchaseApplyIndex(BizPurchaseMainPanel bizPurchaseMainPanel);
}
