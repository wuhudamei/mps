package cn.damei.dao.mobile.home;



import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.entity.mobile.home.BizOrder;

/**
 * 客户评价工人
 * @author Administrator
 *
 */
@MyBatisDao
public interface CustomerEvaluateWorkerDao{

	/**
	 * 查询订单列表
	 * @param customerPhone
	 * @return
	 */
	List<BizOrder> findOrderList(String customerPhone);

	/**
	 * 查询订单
	 * @param bizOrder
	 * @return
	 */
	BizOrder findOrder(BizOrder bizOrder);

	/**
	 * 查询评价列表
	 * @param orderId
	 * @return
	 */
	List<EvaluateWorker> findEvaluateList(Integer orderId);

	/**
	 * 查询评价详情
	 * @param id
	 * @return
	 */
	List<EvaluateWorker> toDetails(Integer id);

	


}
