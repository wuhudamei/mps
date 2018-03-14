package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.DelaySheet;
import cn.damei.entity.modules.DelayBill;
import cn.damei.entity.modules.Dict;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface DelaySheetDao extends CrudDao<DelaySheet>{

	List<DelaySheet> findDelayOrder(Integer id);

	List<Dict> findDelayCategory(int i);

	List<Dict> findDelayCategorytow(String id, int i);

	List<Dict> findDelayCategoryStatus();

	DelaySheet checkSubmit(String orderId, String stageStatus);

	DelaySheet findDelayDetails(DelaySheet delaySheet);

	void pass(DelayBill delayBill);

	void refuse(DelayBill delayBill);

	String checkSubmitOver(String orderId);

    List<Dict> findOrderNodePlan(String orderId);

	boolean isApplyDelay(@Param("map") Map<String, String> map);

	List<Dict> findOrderAllNode(String orderId);

	List<Dict> findTemplateNodePlan(String storeId, String projectMode);

    List<Dict> findScrapDelayBill(String orderId);

	List<Dict> findNodePlanFinsh(@Param("map") Map map);
}
