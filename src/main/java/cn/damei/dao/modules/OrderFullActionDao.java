/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;



import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.entity.modules.OrderFullAction;

@MyBatisDao
public interface OrderFullActionDao extends CrudDao2<OrderFullAction> {

	public List<OrderFullAction> getBudget(String orderId);


	/**
	 * 根据门店和订单编号查询质检模块数据
	 * @param map  orderId
	 * @return  qcCheckNodeName  managerApplyDate  managerExpectPqcDate  pqcAcutalCheckDate  pqcDoneDate
	 */
	List<Map<String,Object>>pqcOrderFullQuery(Map<String,Object> map);
	
	
	
	
	/**
	 * 交底开工查询
	 * @param map
	 * @return
	 */
	public Map<String,Object> disclosureStartsQuery(Map<String,Object> map);


	/**
	 * 查询订单详情
	 * @param map
	 * @return
	 */
	public Map<String, Object> orderDetail(Map<String, Object> map);


	/**
	 * 查看材料详情
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> purchaseDetail(Map<String, Object> map);


	/**
	 * 查看安装阶段
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> installDetail(Map<String, Object> map);
	
	
	/**
	 * 基装变更
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> projectChangeBillQuery(Map<String, Object> map);
	
	
	/**
	 * 款项查询
	 * @param id
	 * @return
	 */
	public List<BizPrePmSettleFin> queryPrePmSettleFinance(String orderId);
}