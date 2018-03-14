package cn.damei.service.mobile.home;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.dao.mobile.home.CustomerEvaluateWorkerDao;




@Service
@Transactional(readOnly=true)
public class CustomerEvaluateWorkerService{
	
	@Autowired
	private CustomerEvaluateWorkerDao dao;


	public List<BizOrder> findOrderList(String customerPhone) {
		List<BizOrder> list = dao.findOrderList(customerPhone);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	

	public BizOrder findOrder(BizOrder bizOrder) {
		return dao.findOrder(bizOrder);
	}


	public List<EvaluateWorker> findEvaluateList(Integer orderId) {
		return dao.findEvaluateList(orderId);
	}


	public List<EvaluateWorker> toDetails(Integer id) {
		
		return dao.toDetails(id);
	}

	


}
