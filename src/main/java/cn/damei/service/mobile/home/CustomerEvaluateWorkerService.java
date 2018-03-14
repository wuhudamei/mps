package cn.damei.service.mobile.home;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.dao.mobile.home.CustomerEvaluateWorkerDao;



/**
 * 客户评价工人
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class CustomerEvaluateWorkerService{
	
	@Autowired
	private CustomerEvaluateWorkerDao dao;

	/**
	 * 查询订单列表
	 * @param customerPhone
	 * @return
	 */
	public List<BizOrder> findOrderList(String customerPhone) {
		List<BizOrder> list = dao.findOrderList(customerPhone);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 查询订单
	 * @param bizOrder
	 * @return
	 */
	public BizOrder findOrder(BizOrder bizOrder) {
		return dao.findOrder(bizOrder);
	}

	/**
	 * 查询评价列表
	 * @param orderId
	 * @return
	 */
	public List<EvaluateWorker> findEvaluateList(Integer orderId) {
		return dao.findEvaluateList(orderId);
	}

	/**
	 * 查询评价详情
	 * @param valueOf
	 * @return
	 */
	public List<EvaluateWorker> toDetails(Integer id) {
		
		return dao.toDetails(id);
	}

	


}
