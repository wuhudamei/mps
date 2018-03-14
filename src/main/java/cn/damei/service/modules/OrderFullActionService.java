
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.dao.modules.OrderFullActionDao;
import cn.damei.entity.modules.OrderFullAction;

@Service
@Transactional(readOnly = true)
public class OrderFullActionService extends CrudService2<OrderFullActionDao, OrderFullAction> {

	public OrderFullAction getBudget(String orderId) {
		
		List<OrderFullAction> list = dao.getBudget(orderId);

		OrderFullAction o = list.get(0);

		boolean flag = true;
		boolean flag2 = true;
		for (OrderFullAction orderFullAction2 : list) {
			

			if (orderFullAction2.getDistributeType() != null) {

				if (flag) {

					if (orderFullAction2.getDistributeType().equals("102")
							|| orderFullAction2.getDistributeType().equals("101")) {
						o.setAssignedManagerDate(orderFullAction2.getAssignedDate());
						o.setAssignedManagerBy(orderFullAction2.getAssignedBy());
						flag = false;
					}

				}


				if (flag2) {
					if (orderFullAction2.getDistributeType().equals("202")
							|| orderFullAction2.getDistributeType().equals("201")) {
						o.setAssignedInspectorDate(orderFullAction2.getAssignedDate());
						o.setAssignedInspectorBy(orderFullAction2.getAssignedBy());
						flag2 = false;
					}

				}
			}
		}
		return o;
	}



	public List<Map<String,Object>>pqcOrderFullQuery(Map<String,Object> map){


		return dao.pqcOrderFullQuery(map);
	}



	public Map<String, Object> orderDetail(Map<String, Object> map) {
		
		return dao.orderDetail(map);
	}



	public List<Map<String, Object>> purchaseDetail(Map<String, Object> map) {
		return dao.purchaseDetail(map);
	}
	
	
	
	

	public Map<String,Object> disclosureStartsQuery(Map<String,Object> map){
		return dao.disclosureStartsQuery(map);
	}



	public List<Map<String, Object>> installDetail(Map<String, Object> map) {
		return dao.installDetail(map);
	}


	
	

	public List<Map<String,Object>> projectChangeBillQuery(Map<String, Object> map){
		return dao.projectChangeBillQuery(map);
	}

	public List<BizPrePmSettleFin> queryPrePmSettleFinance(String orderId){
		return dao.queryPrePmSettleFinance(orderId);
	}
    
}