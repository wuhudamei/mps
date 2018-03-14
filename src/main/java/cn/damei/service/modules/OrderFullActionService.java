/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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
		// 标记 控制 分配
		boolean flag = true;
		boolean flag2 = true;
		for (OrderFullAction orderFullAction2 : list) {
			
			//分配类型不为bull
			if (orderFullAction2.getDistributeType() != null) {
				// 项目经理 取时间最晚的
				if (flag) {

					if (orderFullAction2.getDistributeType().equals("102")
							|| orderFullAction2.getDistributeType().equals("101")) {
						o.setAssignedManagerDate(orderFullAction2.getAssignedDate());
						o.setAssignedManagerBy(orderFullAction2.getAssignedBy());
						flag = false;
					}

				}

				// 质检 取时间最晚的
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


	/**
	 * 根据门店和订单编号查询质检模块数据
	 * @param map  orderId
	 * @return   qcCheckNodeName  managerApplyDate  managerExpectPqcDate  pqcAcutalCheckDate  pqcDoneDate
	 */
	public List<Map<String,Object>>pqcOrderFullQuery(Map<String,Object> map){


		return dao.pqcOrderFullQuery(map);
	}


	/**
	 * 查询订单详情
	 * @param map (orderNumber,storeId)
	 * @return
	 */
	public Map<String, Object> orderDetail(Map<String, Object> map) {
		
		return dao.orderDetail(map);
	}


	/**
	 * 查看材料详情
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> purchaseDetail(Map<String, Object> map) {
		return dao.purchaseDetail(map);
	}
	
	
	
	
	/**
	 * 交底开工
	 * @param map
	 * @return
	 */
	public Map<String,Object> disclosureStartsQuery(Map<String,Object> map){
		return dao.disclosureStartsQuery(map);
	}


	/**
	 * 查看安装阶段
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> installDetail(Map<String, Object> map) {
		return dao.installDetail(map);
	}


	
	
	/**
	 * 基装变更
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> projectChangeBillQuery(Map<String, Object> map){
		return dao.projectChangeBillQuery(map);
	}
	/**
	 * 款项查询
	 * @param orderId
	 * @return
	 */
	public List<BizPrePmSettleFin> queryPrePmSettleFinance(String orderId){
		return dao.queryPrePmSettleFinance(orderId);
	}
    
}