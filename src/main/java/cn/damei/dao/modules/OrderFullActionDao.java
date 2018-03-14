
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



	List<Map<String,Object>>pqcOrderFullQuery(Map<String,Object> map);
	
	
	
	

	public Map<String,Object> disclosureStartsQuery(Map<String,Object> map);



	public Map<String, Object> orderDetail(Map<String, Object> map);



	public List<Map<String, Object>> purchaseDetail(Map<String, Object> map);



	public List<Map<String, Object>> installDetail(Map<String, Object> map);
	
	

	public List<Map<String,Object>> projectChangeBillQuery(Map<String, Object> map);
	
	

	public List<BizPrePmSettleFin> queryPrePmSettleFinance(String orderId);
}