package cn.damei.dao.mobile.home;



import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.entity.mobile.home.BizOrder;


@MyBatisDao
public interface CustomerEvaluateWorkerDao{


	List<BizOrder> findOrderList(String customerPhone);


	BizOrder findOrder(BizOrder bizOrder);


	List<EvaluateWorker> findEvaluateList(Integer orderId);


	List<EvaluateWorker> toDetails(Integer id);

	


}
